package com.kaishengit.tms.scenic.api;


import com.kaishengit.tms.entity.ScenicAccount;
import com.kaishengit.tms.result.AjaxResult;
import com.kaishengit.tms.system.service.ScenicAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 * Created by hoyt on 2017/12/13.
 */
@Controller
public class HomeController {

    @Autowired
    private ScenicAccountService scenicAccountService;


    /**
     * 去登录页面
     * @return
     */
    @GetMapping("/")
    public String index() {

        Subject subject = SecurityUtils.getSubject();

       if(subject.isAuthenticated()) {
            //认为用户是要切换账号
            subject.logout();
        }
        if(!subject.isAuthenticated() && subject.isRemembered()) {
            //被记住的用户直接去登录成功页面
            return "redirect:/home";
        }
        return "index";
    }

    /**
     * 表单登录方法
     * @param scenicAccount
     * @param password
     * @return
     */
    @PostMapping("/")
    public String login(String scenicAccount, String password, boolean rememberMe,
                        RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(scenicAccount,new Md5Hash(password).toString(),rememberMe);
            subject.login(usernamePasswordToken);

            //跳转到登录前访问的URL
            String url = "/home";
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if(savedRequest != null) {
                //获取登录前访问的URL
                url = savedRequest.getRequestUrl();
            }

            //登录成功，记录日志
            String ip = request.getRemoteAddr();
            scenicAccountService.saveLoginLog((ScenicAccount) subject.getPrincipal(),ip);

            return "redirect:"+url;
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
            return "redirect:/";
        } catch (AuthorizationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","您没有访问该系统的权限");
            return "redirect:/";
        }
    }


    /**
     * 安全退出
     * @return
     */
    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","你已安全退出系统");
        return "redirect:/";
    }

    /**
     * 去登录后的页面
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 更改密码
     * @return
     */
    @GetMapping("/profile")
    public String changePassword(Model model) {
        Subject subject = SecurityUtils.getSubject();
        ScenicAccount scenicAccount = (ScenicAccount) subject.getPrincipal();
        model.addAttribute("scenicAccount",scenicAccount);
        return "profile";
    }

    @PostMapping("/profile")
    @ResponseBody
    public AjaxResult changePassword(String newPassword, String confirmPassword,String password) {
        Subject subject = SecurityUtils.getSubject();
        ScenicAccount scenicAccount = (ScenicAccount) subject.getPrincipal();
        try {
            scenicAccountService.updatePassword(scenicAccount,password,newPassword,confirmPassword);
            //重新登录
            subject.logout();
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }

}

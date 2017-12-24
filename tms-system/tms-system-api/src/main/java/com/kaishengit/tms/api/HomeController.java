package com.kaishengit.tms.api;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.result.AjaxResult;
import com.kaishengit.tms.system.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
 * Created by hoyt on 2017/12/24.
 */
@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    /**
     * 登录验证
     * @return
     */
    @GetMapping("/")
    public String login() {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        if (!subject.isAuthenticated() && subject.isRemembered()) {
            return "redirect:/home";
        }
        return "account/login";
    }

    @PostMapping("/")
    public String login(String accountMobile, String accountPassword, boolean rememberMe,
                        RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(accountMobile,new Md5Hash(accountPassword).toString(), rememberMe);
            subject.login(usernamePasswordToken);

            String url = "/account/home";
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if (savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }
            //登陆成功前记录日志
            String ip = request.getRemoteAddr();
            accountService.saveLoginLog((Account) subject.getPrincipal(), ip);

            return "redirect:" + url;
        }catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
            return "redirect:/";
        }
    }

    /**
     * 首页
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "account/home";
    }

    /**安全退出
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/exit")
    public String exit(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","已安全退出");
        return "redirect:/";
    }

    /**
     * 更改密码
     * @return
     */
    @GetMapping("/account/profile")
    public String changePassword(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        model.addAttribute("account",account);
        return "account/profile";
    }

    @PostMapping("/account/profile")
    @ResponseBody
    public AjaxResult changePassword(String newPassword, String confirmPassword,
                                     String password) {
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        try {
            accountService.changePassword(account,password,newPassword,confirmPassword);
            //重新登录
            subject.logout();
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }
}

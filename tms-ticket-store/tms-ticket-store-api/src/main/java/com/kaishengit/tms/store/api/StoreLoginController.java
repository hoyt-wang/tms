package com.kaishengit.tms.store.api;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.result.AjaxResult;
import com.kaishengit.tms.system.service.StoreAccountService;
import com.kaishengit.tms.system.service.StoreService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 售票点登录
 * Created by hoyt on 2017/12/24.
 */
@Controller
public class StoreLoginController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreAccountService storeAccountService;

    @GetMapping("/")
    public String login() {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        if (subject.isAuthenticated() & subject.isRemembered()) {
            return "store/home";
        }
        return "store/login";
    }

    @PostMapping("/")
    public String login(String storeAccount, String storePassword, boolean rememberMe,
                        HttpServletRequest request, RedirectAttributes redirectAttributes) {

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(storeAccount,
                    new Md5Hash(storePassword).toString(), rememberMe);

            subject.login(usernamePasswordToken);
            String url = "/home";
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if (savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }
            String ip = request.getRemoteAddr();
            storeService.saveLoginLog(ip, (StoreAccount) subject.getPrincipal());
            return "redirect:" + url;
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");
            return "redirect:/";
        }

    }

    @GetMapping("/exit")
    public String exit(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","您已安全退出");
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home() {
        return "store/home";
    }

    /**
     * 更改密码
     * @return
     */
    @GetMapping("/profile")
    public String changePassword(Model model) {
        Subject subject = SecurityUtils.getSubject();
        StoreAccount storeAccount = (StoreAccount) subject.getPrincipal();
        model.addAttribute("storeAccount",storeAccount);
        return "profile";
    }

    @PostMapping("/profile")
    @ResponseBody
    public AjaxResult changePassword(String newPassword, String confirmPassword, String password) {
        Subject subject = SecurityUtils.getSubject();
        StoreAccount storeAccount = (StoreAccount) subject.getPrincipal();
        try {
            storeAccountService.updatePassword(storeAccount,password,newPassword,confirmPassword);
            //重新登录
            subject.logout();
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }
}

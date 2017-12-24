package com.kaishengit.tms.scenic.api;

import com.kaishengit.tms.entity.Customer;
import com.kaishengit.tms.entity.ScenicAccount;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.result.AjaxResult;
import com.kaishengit.tms.system.service.ScenicService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by hoyt on 2017/12/18.
 */

@Controller
@RequestMapping("/ticket")
public class ScenicController {

    @Autowired
    private ScenicService scenicService;


    /**
     * 刷卡信息校验
     * @return
     */
    @GetMapping("/validate")
    public String validateCustomer() {
        return "ticket/validate";
    }

    /**
     * 刷卡信息校验
     * @param ticketNum
     * @param scenicAccountId
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/validate")
    public String validateCustomer(Integer ticketNum,Integer scenicAccountId, Model model,RedirectAttributes redirectAttributes) {
        try {
            Customer customer = scenicService.validateTicket(ticketNum,scenicAccountId);
            System.out.println("持卡人信息" + customer.toString());
            model.addAttribute("customer",customer);
            redirectAttributes.addFlashAttribute("message","欢迎光临本景区");
            return "redirect:/customer";
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "ticket/validate";
        }

    }



}

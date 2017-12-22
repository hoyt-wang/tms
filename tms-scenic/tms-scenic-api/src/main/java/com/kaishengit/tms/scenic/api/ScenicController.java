package com.kaishengit.tms.scenic.api;

import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.system.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String validateCustomer(Integer ticketNum,Integer scenicAccountId, RedirectAttributes redirectAttributes) {
        try {
            scenicService.validateTicket(ticketNum,scenicAccountId);
            redirectAttributes.addFlashAttribute("message","欢迎光临本景区");
            return "redirect:/home";
        } catch (ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("messgae",e.getMessage());
            return "ticket/validate";
        }

    }

}

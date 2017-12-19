package com.kaishengit.tms.scenic.api;

import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.system.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by hoyt on 2017/12/18.
 */

@Controller
public class ScenicController {

    @Autowired
    private ScenicService scenicService;

    /**
     * 刷卡信息校验
     * @param ticketNum
     * @param scenicAccountId
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/{id:\\d+}/validate")
    public String validateCustomer(Integer ticketNum,Integer scenicAccountId, RedirectAttributes redirectAttributes) {
        try {
            scenicService.validateTicket(ticketNum,scenicAccountId);
            return "redirect:/home";
        } catch (ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("messgae",e.getMessage());
            return "validate";
        }

    }

}

package com.kaishengit.tms.storage.api;

import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.result.AjaxResult;
import com.kaishengit.tms.system.service.StoreAccountService;
import com.kaishengit.tms.system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by hoyt on 2017/12/14.
 */

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StoreAccountService storeAccountService;

    /**
     * 年票入库
     * @return
     */
    @GetMapping("/new")
    public String saveTicket(Model model,RedirectAttributes redirectAttributes) {

        model.addAttribute("lastTicketNum", ticketService.findLastTicketNum());

        return "ticket/new";

    }

    @PostMapping("/new")
    public String saveTicket(Ticket ticket,Integer cardNum ,String invalidCards,RedirectAttributes redirectAttributes) {
        try{
            System.out.println("作废卡号：" + invalidCards.toString());
            ticketService.saveTicket(ticket,cardNum,invalidCards);
            redirectAttributes.addFlashAttribute("message","年票入库成功");
            return "redirect:/home";
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "ticket/new";
        }

    }

    /**
     * 年票下发
     * @param model
     * @return
     */
    @GetMapping("/out")
    public String outTicket(Model model) {
        List<StoreAccount> storeAccountList = storeAccountService.findAll();
        model.addAttribute("storeAccountList",storeAccountList);
        return "ticket/out";
    }

    @PostMapping("/out")
    public String outTicket(Integer cardNum, String invalidCards, RedirectAttributes redirectAttributes, Integer storeAccountId) {
        try {
            ticketService.outTicket(cardNum,storeAccountId,invalidCards);
            redirectAttributes.addFlashAttribute("message","年票下发成功");
            return "redirect:/home";
        } catch(ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "ticket/out";
        }

    }

    /**
     * 年票作废
     * @return
     */
    @GetMapping("/invalid")
    public String invalidTicket() {
        return "ticket/invalid";
    }
    /**
     * @param ticketNum
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/invalid")
    public String invalidTicket(Integer ticketNum, RedirectAttributes redirectAttributes) {
        try {
            ticketService.invalidTicket(ticketNum);
            redirectAttributes.addFlashAttribute("message","年票作废成功！");
            return "ticket/invalid";
        } catch (ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "ticket/invalid";
        }

    }

    /**
     * 年票list
     * @return
     */
    @GetMapping("/list")
    public String showList(Model model) {
        List<String> ticketStateList = ticketService.findAllTicketState();
        model.addAttribute("ticketStateList",ticketStateList);
        return "ticket/list";
    }

 /*   @PostMapping("/list")
    public String showList(String ticketState,Model model) {

        Long count = ticketService.countByTicketState(ticketState);
        model.addAttribute("count",count);
        return "ticket/list";

    }*/

    @PostMapping("/list")
    @ResponseBody
    public AjaxResult showList(String ticketState) {
        try {
            Long count = ticketService.countByTicketState(ticketState);
            return AjaxResult.successWithData(count);
        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }
}

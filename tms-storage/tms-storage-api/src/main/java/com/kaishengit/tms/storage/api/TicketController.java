package com.kaishengit.tms.storage.api;

import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.storage.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by hoyt on 2017/12/14.
 */

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * 年票入库
     * @return
     */
    @GetMapping("/new")
    public String saveTicket() {
        return "ticket/new";
    }

    @PostMapping("/new")
    public String saveTicket(Ticket ticket, Integer cardNum ,RedirectAttributes redirectAttributes) {
       try{
           ticketService.saveTicket(ticket,cardNum);
           redirectAttributes.addFlashAttribute("message","年票入库成功");
           return "redirect:/ticket/home";
       } catch (ServiceException e) {
           e.printStackTrace();
           redirectAttributes.addFlashAttribute("message",e.getMessage());
           return "ticket/new";
       }
    }

    /**
     * 年票下发
     * @return
     */
    @GetMapping("/out")
    public String outTicket(Integer ticketNum,Integer cardNum,RedirectAttributes redirectAttributes,Integer storeAccountId) {
        try {
            ticketService.outTicket(ticketNum,cardNum,storeAccountId);
            redirectAttributes.addFlashAttribute("message","年票下发成功");
            return "redirect:/ticket/home";
        } catch(ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "ticket/out";
        }

    }

    @GetMapping("/{id:\\d+}/invalid")
    public String invalidTicket(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        ticketService.invalidTicket(id);
        redirectAttributes.addFlashAttribute("message","年票作废成功");
        return "redirect:/ticket/home";
    }

}

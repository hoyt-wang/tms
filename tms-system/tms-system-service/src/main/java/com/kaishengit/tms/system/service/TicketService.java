package com.kaishengit.tms.system.service;

import com.kaishengit.tms.entity.Ticket;

/**
 * 年票业务层
 * Created by hoyt on 2017/12/14.
 */
public interface TicketService {

    /**
     * 年票入库
     * @param ticket
     */
    void saveTicket(Ticket ticket, Integer cardNum, String invalidCards);

    /**
     * 年票下发
     * @param cardNum
     * @param storeAccountId
     */
    void outTicket(Integer cardNum, Integer storeAccountId, String invalidCards);

    /**
     * 年票作废
     * @param ticketNum
     */
    void invalidTicket(Integer ticketNum);
}

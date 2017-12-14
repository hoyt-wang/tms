package com.kaishengit.tms.storage.service;

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
    void saveTicket(Ticket ticket,Integer cardNum);

    /**
     * 年票下发
     * @param ticketNum
     * @param cardNum
     * @param storeAccountId
     */
    void outTicket(Integer ticketNum, Integer cardNum,Integer storeAccountId);

    /**
     * 年票作废
     * @param id
     */
    void invalidTicket(Integer id);
}

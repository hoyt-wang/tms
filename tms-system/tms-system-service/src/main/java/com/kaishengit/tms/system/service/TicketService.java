package com.kaishengit.tms.system.service;

import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.exception.ServiceException;

import java.util.List;

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
    void outTicket(Integer cardNum, Integer storeAccountId, String invalidCards) throws ServiceException;

    /**
     * 年票作废
     * @param ticketNum
     */
    void invalidTicket(Integer ticketNum) throws ServiceException;

    /**
     * 获取最后入库年票卡号
     * @return
     */
    int findLastTicketNum ();

    /**
     * 获取所有售票点状态
     * @return
     */
    List<String> findAllTicketState();

    /**
     * 统计各个年票状态的数量
     * @param ticketState
     * @return
     */
    Long countByTicketState(String ticketState);
}

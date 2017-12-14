package com.kaishengit.tms.storage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.storage.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by hoyt on 2017/12/14.
 */
@Service(version = "1.0",timeout = 5000)
public class TicketServiceImpl implements TicketService{

    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 年票入库
     * @param ticket
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicket(Ticket ticket,Integer cardNum) {

        Ticket ticket2 = new Ticket();

        if (cardNum == 0) {
            ticket2.setTicketNum(ticket.getTicketNum());
            ticket2.setTicketInTime(new Date());
            ticket2.setCreateTime(new Date());
            ticketMapper.insertSelective(ticket2);
            logger.info("添加年票卡号{}",ticket.getTicketNum());
        } else if (cardNum >= 1){
            //年票批量入库
            for (int i = 1 ; i <= cardNum; i++) {

                ticket2.setTicketNum(i + ticket.getTicketNum());
                ticket2.setTicketInTime(new Date());
                ticket2.setCreateTime(new Date());
                ticketMapper.insertSelective(ticket2);
                logger.info("添加年票卡号{}",ticket.getTicketNum() + i);
            }

        } else {
            throw new ServiceException("参数非法");
        }

    }

    /**
     * 年票下发
     * @param ticketNum
     * @param cardNum
     */
    @Override
    public void outTicket(Integer ticketNum, Integer cardNum,Integer storeAccountId) {

     try{
         for (int i = 1 ; i <= cardNum; i++) {
             Ticket ticket = ticketMapper.findByTicketNum(ticketNum + i);
             ticket.setTicketOutTime(new Date());
             ticket.setStoreAccountId(storeAccountId);
             ticketMapper.updateByPrimaryKeySelective(ticket);
             logger.info("下发年票卡号给售票点{}",storeAccountId);
         }
     }catch (ServiceException e) {
         throw new ServiceException("下发年票失败");
     }
    }

    /**
     * 年票作废
     * @param id
     */
    @Override
    public void invalidTicket(Integer id) {

        Ticket ticket = ticketMapper.selectByPrimaryKey(id);
        //年票状态 0未激活  1激活  2作废
        ticket.setTicketState("2");
        ticket.setCreateTime(new Date());
        ticketMapper.updateByPrimaryKey(ticket);
        logger.info("年票{}已作废",ticket.getTicketNum());
    }


}

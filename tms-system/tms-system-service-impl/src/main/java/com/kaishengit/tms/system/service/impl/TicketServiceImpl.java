package com.kaishengit.tms.system.service.impl;

import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.entity.TicketExample;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.system.service.TicketService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyt on 2017/12/14.
 */

@Service
public class TicketServiceImpl implements TicketService {

    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 年票入库
     * @param ticket
     * @param invalidCards 入库前已作废的年票集合
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicket(Ticket ticket,Integer cardNum,String invalidCards) {
        //验证数据库是否有此年票
        Ticket validateTicket = ticketMapper.findByTicketNum(ticket.getTicketNum());

        if (validateTicket == null) {
            int initNum = ticket.getTicketNum();
            //年票状态 0未激活  1激活  2入库  3下发  4作废
            for(int i = 0; i < cardNum; i++) {
                ticket.setTicketNum(i + initNum);
                ticket.setTicketInTime(new Date());
                ticket.setCreateTime(new Date());
                ticket.setTicketState("入库");
                if (StringUtils.isNotEmpty(invalidCards)) {
                    String[] arrays =invalidCards.split(",");
                    List<String> ticketNumList = Arrays.asList(arrays);
                    for (int j = 0; j < ticketNumList.size(); j++) {
                        if ((Integer.valueOf(ticketNumList.get(j))) == (i + initNum)) {
                            ticket.setTicketState("作废");
                        }
                    }
                }
                ticketMapper.insert(ticket);
                logger.info("年票{}入库",ticket.getTicketNum());
            }
        } else {
            throw new ServiceException("起始年票输入错误或已存在，请重新输入");
        }

    }

    /**
     * 年票下发
     * @param cardNum
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void outTicket(Integer cardNum,Integer storeAccountId,String invalidCards) {

            int invalidNum = 0;
            if(StringUtils.isNotEmpty(invalidCards)) {
                //前端手写的损坏的年票
                String[] arrays = invalidCards.split(",");
                List<String> ticketNumList =Arrays.asList(arrays);
                invalidNum = ticketNumList.size();
                List<Ticket> ticketList = null ;
                for (String ticketNum : ticketNumList) {
                    Ticket ticket = ticketMapper.findByTicketNum(Integer.valueOf(ticketNum));
                    ticket.setTicketState("作废");
                    ticketMapper.updateByPrimaryKey(ticket);
                }
            }
             //下发年票
            List<Ticket> tickets = ticketMapper.findByTicketState();
            if ((tickets.size() - invalidNum) >= cardNum) {
                List<Ticket> outTicketList = ticketMapper.findByTicketStateAndCarNum(cardNum);
                for (int i = 0; i < cardNum; i++) {
                    for (Ticket ticket : outTicketList) {
                        ticket.setTicketOutTime(new Date());
                        ticket.setTicketState("下发");
                        ticket.setStoreAccountId(storeAccountId);
                        ticketMapper.updateByPrimaryKeySelective(ticket);
                        logger.info("下发年票卡号{}给售票点{}",ticket.getTicketNum(),storeAccountId);
                    }
                }

            } else {
                throw new ServiceException("库存不足");
            }

    }

    /**
     * 年票作废
     * @param ticketNum
     */
    @Override
    public void invalidTicket(Integer ticketNum) {
        Ticket ticket = ticketMapper.findByTicketNum(ticketNum);
        if (ticket != null) {
            //年票状态 0未激活  1激活  2入库  3下发  4作废
            ticket.setTicketState("作废");
            ticket.setUpdateTime(new Date());
            ticketMapper.updateByPrimaryKey(ticket);
            logger.info("年票{}已作废",ticket.getTicketNum());
        } else {
            throw new ServiceException("无法找到对应的年票，请重新输入卡号");
        }


    }

    /**
     * 获取最后入库年票卡号
     * @return
     */
    @Override
    public int findLastTicketNum() {
        int lastTicketNum ;
        try{
            lastTicketNum = ticketMapper.findLastTicketNum();
            return lastTicketNum;
        } catch (RuntimeException e) {
            return lastTicketNum = 100001 ;
        }

     }

    /**
     * 获取所有售票点状态
     *
     * @return
     */
    @Override
    public List<String> findAllTicketState() {
        return ticketMapper.findAllTicketState();
    }

    /**
     * 统计各个年票状态的数量
     *
     * @param ticketState
     * @return
     */
    @Override
    public Long countByTicketState(String ticketState) {
        return ticketMapper.countByTicketState(ticketState);
    }



}

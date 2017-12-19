package com.kaishengit.tms.system.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.entity.TicketExample;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.system.service.TicketService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by hoyt on 2017/12/14.
 */
@Service(version = "1.0",timeout = 5000)
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
            //年票状态 0未激活  1激活  2入库  3下发  4作废
            for(int i = 0; i <= cardNum; i++) {
                if (StringUtils.isEmpty(invalidCards)) {
                    ticket.setTicketNum(i + ticket.getTicketNum());
                    ticket.setTicketInTime(new Date());
                    ticket.setCreateTime(new Date());
                } else {
                    String[] arrays =invalidCards.split(",");
                    List<String> ticketNumList = Arrays.asList(arrays);
                    for (int j = 0; j < ticketNumList.size(); j++) {
                        if (ticketNumList.get(j).equals(ticket.getTicketNum())) {
                            ticket.setTicketState("作废");
                        }
                    }
                }
            }
            ticketMapper.insertSelective(ticket);
            logger.info("年票{}入库成功",ticket.getTicketNum());
        } else {
            throw new ServiceException("起始年票输入错误或已存在，请重新输入");
        }

    }

    /**
     * 年票下发
     * @param cardNum
     */
    @Override
    public void outTicket(Integer cardNum,Integer storeAccountId,String invalidCards) {

            List<String> ticketNumList = null;
            if(StringUtils.isNotEmpty(invalidCards)) {
                //前端手写的损坏的年票
                String[] arrays = invalidCards.split(",");
                ticketNumList =Arrays.asList(arrays);
                List<Ticket> ticketList = null ;
                for (String ticketNum : ticketNumList) {
                    TicketExample ticketExample = new TicketExample();
                    ticketExample.createCriteria().andTicketNumEqualTo(Integer.valueOf(ticketNum));
                    ticketList = ticketMapper.selectByExample(ticketExample);
                }

                for (Ticket ticket : ticketList) {
                    ticket.setTicketState("作废");
                    ticketMapper.updateByPrimaryKey(ticket);
                }

            }

             //下发年票
            List<Ticket> ticketList = ticketMapper.findByTicketStateAndCarNum(cardNum);
            Ticket ticket = new Ticket();
            cardNum += ticketNumList.size();
            if (ticketList.size() >= cardNum) {
                for (int i = 0; i < cardNum; i++) {
                    ticket.setTicketOutTime(new Date());
                    ticket.setTicketState("下发");
                    ticket.setStoreAccountId(storeAccountId);
                }
                ticketMapper.updateByPrimaryKeySelective(ticket);
                logger.info("下发年票卡号{}给售票点{}",ticket.getTicketNum(),storeAccountId);
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

        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketNum);
        //年票状态 0未激活  1激活  2入库  3下发  4作废
        ticket.setTicketState("作废");
        ticket.setUpdateTime(new Date());
        ticketMapper.updateByPrimaryKey(ticket);
        logger.info("年票{}已作废",ticket.getTicketNum());

    }


}

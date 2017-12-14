package com.kaishengit.tms.storage.service.impl;

import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.storage.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by hoyt on 2017/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-config.xml")
public class TicketServiceImplTest {

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void saveTicket() throws Exception {

        Ticket ticket = new Ticket();
        int cardNum = 5;
        for (int i = 0 ; i <= cardNum; i++) {

            ticket.setTicketNum(i + 1001);
            ticket.setTicketInTime(new Date());
            ticket.setCreateTime(new Date());
            ticketMapper.insertSelective(ticket);
            //logger.info("添加年票卡号{}",ticket.getTicketNum() + i);
        }
    }

}
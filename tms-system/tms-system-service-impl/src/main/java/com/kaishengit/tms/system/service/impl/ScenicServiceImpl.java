package com.kaishengit.tms.system.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.entity.TicketConsumer;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.TicketConsumerMapper;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.system.service.ScenicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

/**
 * Created by hoyt on 2017/12/18.
 */
@Service(version = "1.0",timeout = 5000)
public class ScenicServiceImpl implements ScenicService {

    private static Logger logger = LoggerFactory.getLogger(ScenicAccountServiceImpl.class);


    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private TicketMapper ticketMapper;

    private TicketConsumerMapper ticketConsumerMapper;

    /**
     * 刷卡信息校验
     * @param ticketNum
     */
    @Override
    public void validateTicket(Integer ticketNum, Integer scenicAccountId) {

        Ticket ticket = ticketMapper.findByTicketNum(ticketNum);
        if(ticket != null ) {
            Jedis jedis = jedisPool.getResource();
            //该方法返回填入成功元素的个数
            Long resultNum = jedis.sadd("customer:"+ticket.getCustomerId(),"scenicAccountId:"+ scenicAccountId);

            if(resultNum == 0) {
                throw new ServiceException("该卡已刷，不能再次入园");
            } else {
                if (ticket.getTicketState() == "冻结") {
                    throw new ServiceException("该卡已冻结，请重新激活");
                } else {
                    TicketConsumer ticketConsumer = new TicketConsumer();
                    ticketConsumer.setCreateTime(new Date());
                    //状态 0 结算 1未结算
                    ticketConsumer.setSettlement((byte)1);
                    ticketConsumer.setScenicAccountId(scenicAccountId);
                    ticketConsumerMapper.insertSelective(ticketConsumer);
                    logger.info("用户{}验票通过",ticket.getCustomerId());
                }
            }
        } else {
          throw new ServiceException("该年票无效，没找到相关信息");
        }

    }
}

package com.kaishengit.tms.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.*;
import com.kaishengit.tms.system.service.ScenicService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by hoyt on 2017/12/18.
 */
@Service
public class ScenicServiceImpl implements ScenicService {

    private static Logger logger = LoggerFactory.getLogger(ScenicAccountServiceImpl.class);

    @Autowired
    private ScenicMapper scenicMapper;

    @Autowired
    private ScenicAccountMapper scenicAccountMapper;


    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketConsumerMapper ticketConsumerMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageInfo<Scenic> findAllByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,10);
        ScenicExample scenicExample = new ScenicExample();
        List<Scenic> scenicList = scenicMapper.selectByExample(scenicExample);
        return new PageInfo<>(scenicList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ScenicAccount newScenic(Scenic scenic) {
        //添加景区信息
        scenic.setCreateTime(new Date());
        scenicMapper.insert(scenic);
        //新增景区账号
        ScenicAccount scenicAccount = new ScenicAccount();
        scenicAccount.setCreateTime(new Date());
        scenicAccount.setScenicAccount(scenic.getScenicTel());
        scenicAccount.setScenicId(scenic.getId());
        String password = new Md5Hash("123").toString();
        scenicAccount.setScenicPassword(password);
        scenicAccount.setScenicState("正常营业");
        scenicAccountMapper.insert(scenicAccount);
        //景区添加scenicId
        scenic.setScenicAccountId(scenicAccount.getId());
        scenicMapper.updateByPrimaryKey(scenic);

        logger.info("添加新景区{},景区id为",scenic.getScenicName(),scenicAccount.getId());
        return scenicAccount;
    }

    @Override
    public Scenic findById(Integer scenicId) {

        Scenic scenic = scenicMapper.selectByPrimaryKey(scenicId);
        return scenic;
    }

    @Override
    public void edit(Scenic scenic) {
        scenic.setUpdateTime(new Date());
        scenicMapper.updateByPrimaryKeyWithBLOBs(scenic);
        logger.info("修改景区{}状态",scenic.getScenicName());
    }

    /**
     * 刷卡信息校验
     * @param ticketNum
     */
    @Override
    @Transactional
    public Customer validateTicket(Integer ticketNum, Integer scenicAccountId) {

        Ticket ticket = ticketMapper.findByTicketNum(ticketNum);
        if(ticket != null ) {
          /*  //判断该卡是否过期
            //ticket_validaty_start激活时间 ticket_validaty_end 禁用时间
            String endTime = String.valueOf(ticket.getTicketValidatyEnd());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTime invalidTime = formatter.parseDateTime(endTime);

            if(invalidTime.isAfterNow()) {
                //刷卡信息记录到redis 第一次 | 重复  ps:每晚12点 清楚redis记录

            } else {
                throw new ServiceException("该卡已过期，请重新激活");
            }*/

            Jedis jedis = jedisPool.getResource();
            //该方法返回填入成功元素的个数
            Long resultNum = jedis.sadd(String.valueOf(scenicAccountId),"customer:"+ticket.getCustomerId());
            if(resultNum != 0) {
                if (ticket.getTicketState().equals("正常")) {

                    //TODO 判断改卡是否有优惠 通过年票获得持卡人信息 免费|优惠|正常
                    TicketConsumer ticketConsumer = new TicketConsumer();
                    ticketConsumer.setId(UUID.randomUUID().toString());
                    ticketConsumer.setCreateTime(new Date());
                    //添加消费记录  0结算 | 1未结算
                    ticketConsumer.setSettlement((byte)1);
                    ticketConsumer.setTicketId(ticket.getId());
                    ticketConsumer.setScenicAccountId(scenicAccountId);
                    ticketConsumerMapper.insertSelective(ticketConsumer);
                    logger.info("用户{}验票通过",ticket.getCustomerId());


                    return customerMapper.selectByPrimaryKey(ticket.getCustomerId());
                } else {
                    throw new ServiceException("该卡已冻结或者作废，请重新激活或办卡");
                }
            } else {
                throw new ServiceException("该卡已刷，不能再次入园");
            }
        } else {
          throw new ServiceException("该年票无效，没找到相关信息");
        }

    }

    /**
     * 从redis中统计今日客流量
     *
     * @return
     */
    @Override
    public Long countByRedisPool(Integer scenicAccountId) {
        Jedis jedis = jedisPool.getResource();
        return  jedis.scard(String.valueOf(scenicAccountId));
    }
}

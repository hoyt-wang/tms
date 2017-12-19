package com.kaishengit.tms.job;

import com.kaishengit.tms.entity.Customer;
import com.kaishengit.tms.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by hoyt on 2017/12/18.
 */

public class ScenicJob {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private CustomerService customerService;

    /**
     * 每天自动清一次redis
     */
    public void clearRedis() {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            jedis.flushAll();
        }
    }

    //每年更改一次年龄
    public void updateAge() {
        customerService.updateAge();
    }
}

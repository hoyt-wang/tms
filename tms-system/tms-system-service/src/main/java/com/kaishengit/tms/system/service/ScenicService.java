package com.kaishengit.tms.system.service;

import com.kaishengit.tms.entity.Customer;

/**
 * Created by hoyt on 2017/12/18.
 */
public interface ScenicService {

    /**
     * 验证年票是否有效
     * @param ticketNum
     */
    Customer validateTicket(Integer ticketNum, Integer scenicAccountId);

    /**
     * 从redis中统计今日客流量
     * @return
     */
    Long countByRedisPool(Integer scenicAccountId);
}

package com.kaishengit.tms.system.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.Customer;
import com.kaishengit.tms.entity.Scenic;
import com.kaishengit.tms.entity.ScenicAccount;

/**
 * Created by hoyt on 2017/12/18.
 */

public interface ScenicService {


    /**
     * 查询风景区列表并分页
     * @param pageNo
     * @return
     */
    PageInfo<Scenic> findAllByPageNo(Integer pageNo);

    /**
     * 新增景区同时添加景区账号
     * @param scenic
     * @return
     */
    ScenicAccount newScenic(Scenic scenic);

    /**
     * 修改景区状态
     * @param scenicId
     * @return
     */
    Scenic findById(Integer scenicId);

    /**
     * 修改景区状态
     * @param scenic
     */
    void edit(Scenic scenic);


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

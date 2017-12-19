package com.kaishengit.tms.system.service;

/**
 * Created by hoyt on 2017/12/18.
 */
public interface ScenicService {

    /**
     * 验证年票是否有效
     * @param ticketNum
     */
    void validateTicket(Integer ticketNum, Integer scenicAccountId);
}

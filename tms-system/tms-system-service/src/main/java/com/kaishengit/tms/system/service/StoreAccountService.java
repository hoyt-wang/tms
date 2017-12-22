package com.kaishengit.tms.system.service;

import com.kaishengit.tms.entity.StoreAccount;

import java.util.List;

/**
 * Created by hoyt on 2017/12/21.
 */
public interface StoreAccountService {

    /**
     * 获取所有售票点账户
     * @return
     */
    List<StoreAccount> findAll();
}

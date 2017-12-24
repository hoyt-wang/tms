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

    /**
     * 更改密码
     * @param storeAccount
     * @param password
     * @param newPassword
     * @param confirmPassword
     */
    void updatePassword(StoreAccount storeAccount, String password, String newPassword, String confirmPassword);
}

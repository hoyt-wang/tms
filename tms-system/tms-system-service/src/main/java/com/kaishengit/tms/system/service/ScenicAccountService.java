package com.kaishengit.tms.system.service;


import com.kaishengit.tms.entity.Role;
import com.kaishengit.tms.entity.ScenicAccount;

import java.util.List;

/**
 * Created by hoyt on 2017/12/18.
 */

public interface ScenicAccountService {


    /**
     * 根据手机号码查询ScenicAccount对象
     * @param scenicAccount
     * @return
     */
    ScenicAccount findScenicAccountByScenicAccount(String scenicAccount);

    /**
     * 记录用户登录的日志
     * @param scenicAccount
     * @param ip
     */
    void saveLoginLog(ScenicAccount scenicAccount, String ip);

    /**
     * 更改密码
     * @param scenicAccount
     * @param password  原始密码
     * @param newPassword  新密码
     * @param confirmPassword  确认密码
     */
    void updatePassword(ScenicAccount scenicAccount, String password, String newPassword, String confirmPassword);
}

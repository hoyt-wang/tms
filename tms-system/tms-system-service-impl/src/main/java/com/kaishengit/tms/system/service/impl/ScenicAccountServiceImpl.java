package com.kaishengit.tms.system.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.ScenicAccount;
import com.kaishengit.tms.entity.ScenicAccountExample;
import com.kaishengit.tms.entity.ScenicLoginLog;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.ScenicAccountMapper;
import com.kaishengit.tms.mapper.ScenicLoginLogMapper;
import com.kaishengit.tms.system.service.ScenicAccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by hoyt on 2017/12/13.
 */
@Service(version = "1.0",timeout = 5000)
public class ScenicAccountServiceImpl implements ScenicAccountService {

    private static Logger logger = LoggerFactory.getLogger(ScenicAccountServiceImpl.class);

    @Autowired
    private ScenicAccountMapper scenicAccountMapper;

    @Autowired
    private ScenicLoginLogMapper scenicLoginLogMapper;

    /**
     * 根据手机号码查询ScenicAccount对象
     * @param scenicAccount
     * @return
     */
    @Override
    public ScenicAccount findScenicAccountByScenicAccount(String scenicAccount) {
        ScenicAccountExample scenicAccountExample = new ScenicAccountExample();
        scenicAccountExample.createCriteria().andScenicAccountEqualTo(scenicAccount);
        List<ScenicAccount> scenicAccountList = scenicAccountMapper.selectByExample(scenicAccountExample);
        if(scenicAccountList != null && !scenicAccountList.isEmpty()) {
            return scenicAccountList.get(0);
        }
        return null;
    }

    /**
     * 记录用户登录的日志
     *
     * @param scenicAccount
     * @param ip
     */
    @Override
    public void saveLoginLog(ScenicAccount scenicAccount, String ip) {

        ScenicLoginLog scenicLoginLog = new ScenicLoginLog();
        scenicLoginLog.setScenicAccountId(scenicAccount.getId());
        scenicLoginLog.setLoginIp(ip);
        scenicLoginLog.setLoginTime(new Date());
        scenicLoginLogMapper.insertSelective(scenicLoginLog);
        logger.info("scenicAccountLogin: {} - {} - {}",scenicAccount.getId(),new Date(),ip);
    }

    /**
     * 更改密码
     * @param scenicAccount
     * @param password  原始密码
     * @param newPassword  新密码
     * @param confirmPassword 确认密码
     */
    @Override
    public void updatePassword(ScenicAccount scenicAccount, String password, String newPassword, String confirmPassword) {
        //获取原始密码
        String oldPwd = scenicAccount.getScenicPassword();
        String md5OldPwd = DigestUtils.md5Hex(password);
        //验证输入的原始密码
        if (md5OldPwd.equals(oldPwd)) {
            //验证新密码是否一致
            if (newPassword.equals(confirmPassword)) {
                if (!(DigestUtils.md5Hex(newPassword).equals(oldPwd))) {
                    scenicAccount.setScenicPassword(DigestUtils.md5Hex(newPassword));
                    scenicAccountMapper.updateByPrimaryKey(scenicAccount);
                } else {
                    throw new ServiceException("密码没有改动");
                }
            } else {
                throw new ServiceException("两次输入密码不一致");
            }
        } else {
            throw new ServiceException("旧密码输入错误");
        }

    }


}

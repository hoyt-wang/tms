package com.kaishengit.tms.system.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.StoreAccountExample;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.StoreAccountMapper;
import com.kaishengit.tms.system.service.StoreAccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hoyt on 2017/12/21.
 */

@Service(version = "1.0",timeout = 5000)
public class StoreAccountServiceImpl implements StoreAccountService {

    @Autowired
    private StoreAccountMapper storeAccountMapper;
    /**
     * 获取所有售票点账户
     *
     * @return
     */
    @Override
    public List<StoreAccount> findAll() {
        StoreAccountExample storeAccountExample = new StoreAccountExample();
        return storeAccountMapper.selectByExample(storeAccountExample);
    }

    /**
     * 更改密码
     *
     * @param storeAccount
     * @param password
     * @param newPassword
     * @param confirmPassword
     */
    @Override
    public void updatePassword(StoreAccount storeAccount, String password, String newPassword, String confirmPassword) {
        String oldpwd = storeAccount.getStorePassword();
        String md5Pwd = DigestUtils.md5Hex(password);
        if(md5Pwd.equals(oldpwd)){ //输入的旧密码与原密码一致
            if(newPassword.equals(confirmPassword)){//判断输入的两个新密码是否一致
                if(!(DigestUtils.md5Hex(newPassword).equals(oldpwd))){//如果新密码与原密码不同，执行更新密码操作
                    storeAccount.setStorePassword(DigestUtils.md5Hex(newPassword));
                    storeAccountMapper.updateByPrimaryKey(storeAccount);
                }else if(DigestUtils.md5Hex(newPassword).equals(oldpwd)){
                    throw new ServiceException("密码没有改动");
                }
            }else{//抛出异常
                throw new ServiceException("抱歉，密码输入不一致");
            }
        }else{//抛出异常
            throw new ServiceException("旧密码输入错误");
        }
    }
}

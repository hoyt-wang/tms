package com.kaishengit.tms.system.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountExample;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.Role;
import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
import com.kaishengit.tms.mapper.AccountRoleMapper;
import com.kaishengit.tms.system.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by hoyt on 2017/12/13.
 */

@Service(version = "1.0",timeout = 5000)
public class AccountServiceImpl implements AccountService{

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    @Autowired
    private AccountRoleMapper accountRoleMapper;


    /**
     * 根据Account的ID查询所拥有的角色列表
     * @param id
     * @return
     */
    @Override
    public List<Role> findRoleByAccountId(Integer id) {
        return accountRoleMapper.findByAccountId(id);
    }

    /**
     * 根据手机号码查询Account对象
     * @param mobile
     * @return
     */
    @Override
    public Account findAccountByMobile(String mobile) {

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(mobile);

        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList != null && !accountList.isEmpty()) {
            return accountList.get(0);
        }
        return null;
    }

    /**
     * 记录用户登录的日志
     * @param account
     * @param ip
     */
    @Override
    public void saveLoginLog(Account account, String ip) {

        AccountLoginLog loginLog = new AccountLoginLog();
        loginLog.setAccountId(account.getId());
        loginLog.setLoginIp(ip);
        loginLog.setLoginTime(new Date());
        accountLoginLogMapper.insertSelective(loginLog);
        logger.info("accountLogin: {} - {} - {}",account.getId(),new Date(),ip);
    }
}

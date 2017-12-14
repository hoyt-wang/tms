package com.kaishengit.tms.system.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Role;

import java.util.List;

/**
 * TMS账号业务层
 * @author fankay
 */
public interface AccountService {

    /**
     * 根据Account的ID查询所拥有的角色列表
     * @param id
     * @return
     */
    List<Role> findRoleByAccountId(Integer id);

    /**
     * 根据手机号码查询Account对象
     * @param mobile
     * @return
     */
    Account findAccountByMobile(String mobile);

    /**
     * 记录用户登录的日志
     * @param account
     * @param ip
     */
    void saveLoginLog(Account account, String ip);
}

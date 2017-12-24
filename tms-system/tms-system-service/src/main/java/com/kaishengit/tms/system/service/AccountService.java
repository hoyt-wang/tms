package com.kaishengit.tms.system.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Role;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.exception.ServiceException;

import java.util.List;

/**
 * TMS账号业务层
 * Created by hoyt on 2017/12/24.
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

    /**
     * 更改账户密码
     * @param account
     * @param password
     * @param newPassword
     * @param confirmPassword
     */
    void updatePassword(Account account, String password, String newPassword, String confirmPassword) throws ServiceException;


    void saveAccount(Account account,Integer[] roleId) throws ServiceException;

    void deleteAccount(Integer accountId) throws ServiceException;

    Account findAccountById(Integer id);

    void updateAccount(Account account, Integer[] roleIdList);

    PageInfo<Account> findAccountListByPage(Integer pageNo);

    /**
     * 查询角色列表
     * @return
     */
    List<Role> findAllRole();

    /**
     *售票点登陆查询
     * @param userName
     * @return
     */
    StoreAccount findStoreAccountByMobile(String userName);

    /**
     * 更改密码
     * @param account
     * @param password
     * @param newPassword
     * @param confirmPassword
     */
    void changePassword(Account account, String password, String newPassword, String confirmPassword);
}

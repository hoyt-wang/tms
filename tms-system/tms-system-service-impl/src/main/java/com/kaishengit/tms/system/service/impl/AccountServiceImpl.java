package com.kaishengit.tms.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.*;
import com.kaishengit.tms.system.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hoyt on 2017/12/13.
 */

@Service
public class AccountServiceImpl implements AccountService{

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private StoreAccountMapper storeAccountMapper;


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

    /**
     * 更改账户密码
     * @param account
     * @param password
     * @param newPassword
     * @param confirmPassword
     */
    @Override
    public void updatePassword(Account account, String password, String newPassword, String confirmPassword) throws ServiceException{
        String oldPwd = account.getAccountPassword();
        String md5Pwd = DigestUtils.md5Hex(password);
        if (md5Pwd.equals(oldPwd)) {
            if(newPassword.equals(confirmPassword)){
                if(!(DigestUtils.md5Hex(newPassword).equals(oldPwd))){
                    account.setAccountPassword(DigestUtils.md5Hex(newPassword));
                    accountMapper.updateByPrimaryKey(account);
                }else if(DigestUtils.md5Hex(newPassword).equals(oldPwd)){
                    throw new ServiceException("密码没有改动");
                }
            }else{//抛出异常
                throw new ServiceException("抱歉，密码输入不一致");
            }
        } else {
            throw new ServiceException("旧密码输入错误");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveAccount(Account account,Integer[] roleId) throws ServiceException {

        //判断账号是否存在
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(account.getAccountMobile());
        List<Account> accountList = accountMapper.selectByExample(accountExample);

        if (accountList != null && !accountList.isEmpty()) {
            throw new ServiceException("该账号已存在");
        }
        //获取roleList
        List<Role> roleList = null;
        for (Integer id : roleId) {
            Role role = roleMapper.selectByPrimaryKey(id);
            roleList.add(role);
        }
        //添加账户
        account.setCreateTime(new Date());
        account.setAccountState("正常");
        String md5Pwd = DigestUtils.md5Hex(account.getAccountPassword());
        account.setAccountPassword(md5Pwd);
        account.setRoleList(roleList);
        accountMapper.insert(account);
        //添加角色
        for (Integer id : roleId) {
            //添加关联关系
            AccountRoleKey accountRoleKey = new AccountRoleKey();
            accountRoleKey.setAccountId(account.getId());
            accountRoleKey.setRoleId(id);
            accountRoleMapper.insert(accountRoleKey);
            logger.info("新增账户{},角色id为{}",account.getAccountName(),id);
        }
        //TODO 添加一条日志到本地
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteAccount(Integer accountId) throws ServiceException {

        //TODO 判断是否有其他关联关系
        //删除角色id对应 的角色关联表
        AccountRoleExample accountRoleExample = new AccountRoleExample();
        accountRoleExample.createCriteria().andAccountIdEqualTo(accountId);
        accountRoleMapper.deleteByExample(accountRoleExample);
        //删除角色
        accountMapper.deleteByPrimaryKey(accountId);
        logger.info("删除账号,id为{},并删除对应关联关系",accountId);
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAccount(Account account, Integer[] roleIdList) {
        //修改账户
        accountMapper.updateByPrimaryKey(account);
        //修改账户对应的角色

        if (roleIdList != null) {
            //通过accountId删除关联关系
            AccountRoleExample accountRoleExample = new AccountRoleExample();
            accountRoleExample.createCriteria().andAccountIdEqualTo(account.getId());
            accountRoleMapper.deleteByExample(accountRoleExample);
            //创建关联关系
            for (Integer id : roleIdList) {
                AccountRoleKey accountRoleKey = new AccountRoleKey();
                accountRoleKey.setAccountId(account.getId());
                accountRoleKey.setRoleId(id);
                accountRoleMapper.insert(accountRoleKey);
            }
        }
        logger.info("修改账户{},id为{}",account.getAccountName(),account.getId());
    }

    @Override
    public PageInfo<Account> findAccountListByPage(Integer pageNo) {

        PageHelper.startPage(pageNo,10);
        AccountExample accountExample = new AccountExample();
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        return new PageInfo<>(accountList);
    }

    /**
     * 查询角色列表
     * @return
     */
    @Override
    public List<Role> findAllRole() {
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        return roleList;
    }

    /**
     * 售票点登陆查询
     * @param userName
     * @return
     */
    @Override
    public StoreAccount findStoreAccountByMobile(String userName) {

        StoreAccountExample storeAccountExample = new StoreAccountExample();
        storeAccountExample.createCriteria().andStoreAccountEqualTo(userName);
        List<StoreAccount> storeAccounts = storeAccountMapper.selectByExample(storeAccountExample);
        return storeAccounts.get(0);
    }

    /**
     * 更改密码
     *
     * @param account
     * @param password
     * @param newPassword
     * @param confirmPassword
     */
    @Override
    public void changePassword(Account account, String password, String newPassword, String confirmPassword) {
        String oldpwd = account.getAccountPassword();
        String md5Pwd = DigestUtils.md5Hex(password);
        if(md5Pwd.equals(oldpwd)){ //输入的旧密码与原密码一致
            if(newPassword.equals(confirmPassword)){//判断输入的两个新密码是否一致
                if(!(DigestUtils.md5Hex(newPassword).equals(oldpwd))){//如果新密码与原密码不同，执行更新密码操作
                    account.setAccountPassword(DigestUtils.md5Hex( newPassword));
                    accountMapper.updateByPrimaryKey(account);
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

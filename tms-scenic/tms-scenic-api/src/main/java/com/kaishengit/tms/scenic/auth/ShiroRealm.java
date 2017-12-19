package com.kaishengit.tms.scenic.auth;


import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Role;
import com.kaishengit.tms.entity.ScenicAccount;
import com.kaishengit.tms.system.service.ScenicAccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private ScenicAccountService scenicAccountService;

    /**
     * 角色或权限认证使用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的对象
        ScenicAccount scenicAccount = (ScenicAccount) principalCollection.getPrimaryPrincipal();
        //根据登录的对象获取所拥有的角色列表
        //List<Role> roleList = accountService.findRoleByAccountId(account.getId());

        //获取Role集合中的名称，创建字符串列表
       /* List<String> roleNameList = new ArrayList<>();
        for(Role role : roleList) {
            roleNameList.add(role.getRoleName());
        }*/

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //simpleAuthorizationInfo.addRoles(roleNameList);
        return simpleAuthorizationInfo;
    }


    /**
     * 登录认证使用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        ScenicAccount scenicAccount = scenicAccountService.findScenicAccountByScenicAccount(userName);
        if(scenicAccount != null) {
            return new SimpleAuthenticationInfo(scenicAccount,scenicAccount.getScenicPassword(),getName());
        }
        return null;
    }
}

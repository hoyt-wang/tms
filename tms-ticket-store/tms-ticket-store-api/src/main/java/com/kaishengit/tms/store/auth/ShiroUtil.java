package com.kaishengit.tms.store.auth;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by hoyt on 2017/12/24.
 */
public class ShiroUtil {

    public static StoreAccount getCurrentAccount() {

        return (StoreAccount)getSubject().getPrincipal();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}

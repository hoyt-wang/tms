package com.kaishengit.tms.system.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.StoreAccountExample;
import com.kaishengit.tms.mapper.StoreAccountMapper;
import com.kaishengit.tms.system.service.StoreAccountService;
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
}

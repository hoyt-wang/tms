package com.kaishengit.tms.system.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.mapper.CustomerMapper;
import com.kaishengit.tms.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hoyt on 2017/12/18.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 每年更新年龄
     */
    @Override
    public void updateAge() {

        customerMapper.updateAge();
    }
}

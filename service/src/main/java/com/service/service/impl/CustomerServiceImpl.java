package com.service.service.impl;

import com.service.entity.CustomerInfo;
import com.service.mapper.CustomerMapper;
import com.service.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    @Transactional
    public int insertOne(CustomerInfo customer) {
        int result= customerMapper.insertOne(customer);
        return result;
    }

    @Override
    public int chaChong(String custmerNo) {
        int result= customerMapper.chaChong(custmerNo);
        return result;
    }
}

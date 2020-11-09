package com.businness.service.impl;

import com.businness.entity.CustEX;
import com.businness.mapper.CustDaoMapper;
import com.businness.service.CustDaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("custDaoService")
public class CustDaoServiceImpl implements CustDaoService {

    @Resource
    private CustDaoMapper custDaoMapper;

    @Override
    public int chachongname(String custname) {
        return custDaoMapper.chachongname(custname);
    }

    @Override
    public int insertOne(CustEX custEX) {
        return custDaoMapper.insertOne(custEX);
    }

    @Override
    public int chacount() {
        return custDaoMapper.chacount();
    }

    @Override
    public int chaChongCount(String custname) {
        return custDaoMapper.chaChongCount(custname);
    }
}

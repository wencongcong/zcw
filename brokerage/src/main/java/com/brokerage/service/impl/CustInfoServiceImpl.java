package com.brokerage.service.impl;

import com.brokerage.entity.Cust;
import com.brokerage.mapper.CustInfoMapper;
import com.brokerage.service.CustInfoService;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Map;

@Service("custInfoService")
public class CustInfoServiceImpl implements CustInfoService {

    @Resource
    private CustInfoMapper custInfoMapper;

    @Override
    public int Autocontrolledcust(Cust cust) {
        return 0;
    }
}

package com.brokerage.service.impl;

import com.brokerage.entity.Prod;
import com.brokerage.mapper.ProdInMapper;
import com.brokerage.service.ProdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("proService")
public class ProdServiceImpl implements ProdService {

    @Resource
    private ProdInMapper prodMapper;

    @Override
    public Prod queryAlldepa(int id,String depaname) {
        return prodMapper.queryAll(id,depaname);
    }
}

package com.service.service.impl;

import com.service.entity.Cust;
import com.service.mapper.CustMapper;
import com.service.service.CustService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("custService")
public class CustServiceImpl implements CustService {

    @Resource
    private CustMapper custMapper;

    @Override
    public int insertOneCust(Cust cust) {
        return custMapper.insertOneCust(cust);
    }

    @Override
    public int chaChong(String cid) {
        return custMapper.chaChong(cid);
    }


    @Override
    public int chawork(String cid) {
        return custMapper.chawork(cid);
    }

    @Override
    public int chacount() {
        return custMapper.chacount();
    }

    @Override
    public int sgchars(Map map) {
        return custMapper.sgchars(map);
    }

    @Override
    public int chaCustId(String cid) {
        return custMapper.chaCustId(cid);
    }

    @Override
    public int chachongname(String custname) {
        return custMapper.chachongname(custname);
    }

    @Override
    public int chachongcount(String custname) {
        return custMapper.chachongcount(custname);
    }
}

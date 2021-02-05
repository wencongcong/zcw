package com.service.service.impl;

import com.service.entity.Cust;
import com.service.mapper.CustMapper;
import com.service.service.CustService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int sgchars(Cust cust) {
        return custMapper.sgchars(cust);
    }

    @Override
    public int chaCustId(String cid) {
        return custMapper.chaCustId(cid);
    }

    @Override
    public int chachongname(String custname,String custphone,String custidcard) {
        return custMapper.chachongname(custname,custphone,custidcard);
    }

    @Override
    public int chachongcount(String custname,String custphone,String custidcard) {
        return custMapper.chachongcount(custname,custphone,custidcard);
    }

    @Override
    @Transactional
    public int changecust(Map map) {
        return custMapper.changecust(map);
    }
}

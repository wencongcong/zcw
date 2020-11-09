package com.service.service.impl;

import com.service.entity.Orderdetails;
import com.service.mapper.OrderdetailMapper;
import com.service.service.OrderdetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("orderdetailService")
public class OrderdetailServiceImpl implements OrderdetailService {

    @Resource
    private OrderdetailMapper orderdetailMapper;

    @Override
    @Transactional
    public int insertOne(Orderdetails orders) {
        int result=orderdetailMapper.insertOne(orders);
        return result;
    }

    @Override
    public int chaOne(String orderdetilid) {
        int result=orderdetailMapper.chaOne(orderdetilid);
        return result;
    }
}

package com.service.service.impl;

import com.service.entity.OrderInfo;
import com.service.mapper.OrderInfoMapper;
import com.service.service.OrderInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    @Transactional
    public int inserOne(OrderInfo order) {
        int result=orderInfoMapper.inserOne(order);
        return result;
    }

    @Override
    public int chaOne(String orderNo) {
        int result=orderInfoMapper.chaOne(orderNo);
        return result;
    }

    @Override
    public int insertOne(String orderNo) {
        return orderInfoMapper.insertOne(orderNo);
    }

    @Override
    public int chaorderId(String orderno) {
        return orderInfoMapper.chaorderId(orderno);
    }

    @Override
    public String chaStatus(int id) {
        return orderInfoMapper.chaStatus(id);
    }

    @Override
    public List<Map<String,String>> chaOrderNo(String acceptancetime,String ostats) {
        return orderInfoMapper.chaOrderNo(acceptancetime,ostats);
    }

    @Override
    public int udpataeStatus(String orderNo, String status) {
        return orderInfoMapper.uodataeStatus(orderNo,status);
    }

    @Override
    public int chaOrderId(String orderno) {
        return orderInfoMapper.chaOrderId(orderno);
    }

    @Override
    public int insertOneOrderNo(OrderInfo orderInfo) {
        return orderInfoMapper.insertOneOrderNo(orderInfo);
    }

    @Override
    public int updateOneOrder(OrderInfo orderInfo) {
        return orderInfoMapper.updateOneOrder(orderInfo);
    }

    @Override
    public int deleteOrderinfo(String orderid) {
        return orderInfoMapper.deleteOrderinfo(orderid);
    }
}

package com.businness.service.impl;

import com.businness.entity.OrderInfoEX;
import com.businness.mapper.OrderInfosMapper;
import com.businness.service.OrderInfosService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderInfosService")
public class OrderInfosServiceImpl implements OrderInfosService {

    @Resource
    private OrderInfosMapper orderInfosMapper;

    @Override
    public List<OrderInfoEX> allTheQuery() {
        return orderInfosMapper.allTheQuery();
    }

    @Override
    public int chaOrderId(String orderNo) {
        return orderInfosMapper.chaOrderId(orderNo);
    }

    @Override
    public int insertOneOrderNo(OrderInfoEX orderInfo) {
        return orderInfosMapper.insertOneOrderNo(orderInfo);
    }
}

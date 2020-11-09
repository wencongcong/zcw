package com.service.service.impl;

import com.service.entity.OrderHstory;
import com.service.mapper.OrderHstoryMapper;
import com.service.service.OrderHstoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("orderHstoryService")
public class OrderHstoryServiceImpl implements OrderHstoryService {

    @Resource
    private OrderHstoryMapper orderHstoryMapper;

    @Override
    @Transactional
    public int insertOneOrder(OrderHstory orderHstory) {
        int result= orderHstoryMapper.insertOneOrder(orderHstory);
        return result;
    }
}

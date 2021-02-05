package com.businness.service;

import com.businness.entity.OrderInfoEX;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfosService {

    public List<OrderInfoEX> allTheQuery();


    public int chaOrderId(String orderNo);

    public int insertOneOrderNo(OrderInfoEX orderInfo);
}

package com.service.service;

import com.service.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderInfoService {

    //插入
    public int inserOne(OrderInfo order);
    //查重
    public int chaOne(String orderNo);

    public int insertOne(String orderNo);

    //查询ID
    public int chaorderId(String orderno);

    public String chaStatus(int id);

    public List<Map<String,String>> chaOrderNo(String acceptancetime,String ostats);

    public int udpataeStatus(String orderNo,String status);

    //根据7工单查询ID
    public int chaOrderId(String orderno);

    public int insertOneOrderNo(OrderInfo orderInfo);

    public int updateOneOrder(OrderInfo orderInfo);

    public int deleteOrderinfo(String orderid);
}

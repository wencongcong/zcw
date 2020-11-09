package com.service.mapper;

import com.service.entity.OrderInfo;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    //插入
    public int inserOne(OrderInfo order);
    //查询是否重复
    public int chaOne(@Param("orderno")String orderNo);

    public int insertOne(@Param("orderno")String orderno);

    //查询ID
    public int chaorderId(@Param("orderno")String orderno);

    public String chaStatus(@Param("id")int id);

    public List<Map<String,String>> chaOrderNo(@Param("acceptancetime")String acceptancetime,@Param("ostats")String ostats);

    public int uodataeStatus(@Param("orderno")String orderno,@Param("status")String status);

    //根据7工单查询ID
    public int chaOrderId(@Param("orderNo")String orderNo);

    //插入7工单和状态
    public int insertOneOrderNo(OrderInfo orderInfo);

    public int updateOneOrder(OrderInfo orderInfo);
}

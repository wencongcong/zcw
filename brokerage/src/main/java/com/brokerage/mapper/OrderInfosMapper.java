package com.brokerage.mapper;

import com.brokerage.entity.OrderInfo;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfosMapper extends BaseMapper<OrderInfo> {

    public int insertOneOrderNo(OrderInfo orderInfo);

    public int chaOrderId(@Param("orderNo")String orderNo);

    public int insertOne(@Param("orderno")String orderno);

    public int chaorderId(@Param("orderno")String orderno);


}

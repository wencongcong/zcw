package com.businness.mapper;

import com.businness.entity.OrderInfoEX;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfosMapper extends BaseMapper<OrderInfoEX> {
    public List<OrderInfoEX> allTheQuery();

    public int chaOrderId(@Param("orderNo")String orderNo);

    public int insertOneOrderNo(OrderInfoEX orderInfo);
}

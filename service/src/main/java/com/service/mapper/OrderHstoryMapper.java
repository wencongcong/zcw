package com.service.mapper;

import com.service.entity.OrderHstory;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderHstoryMapper extends BaseMapper<OrderHstory> {
    public int insertOneOrder(OrderHstory orderHstory);
}

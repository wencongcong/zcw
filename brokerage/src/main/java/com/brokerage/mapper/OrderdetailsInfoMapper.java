package com.brokerage.mapper;

import com.brokerage.entity.OrderdetailsInfo;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderdetailsInfoMapper extends BaseMapper<OrderdetailsInfo> {

    public List<Map<String,String>> queryAllorder(@Param("ordeinfoid")String ordeinfoid);

}

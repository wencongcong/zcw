package com.service.mapper;

import com.service.entity.Orderdetails;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderdetailMapper extends BaseMapper<Orderdetails> {

    public int insertOne(Orderdetails orders);

    public int chaOne(@Param("orderdetilid")String orderdetilid);

    public int updatepeo(@Param("dealingpeopre")String dealingpeopre,@Param("orderjson")String orderjson,@Param("orderinfoid")String orderinfoid);
}

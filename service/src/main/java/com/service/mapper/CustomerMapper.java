package com.service.mapper;

import com.service.entity.CustomerInfo;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper extends BaseMapper<CustomerInfo> {
    public int insertOne(CustomerInfo customer);

    public int chaChong(@Param("custmerNo")String custmerNo);
}

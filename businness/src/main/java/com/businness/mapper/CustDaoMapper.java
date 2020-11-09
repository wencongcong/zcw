package com.businness.mapper;

import com.businness.entity.CustEX;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustDaoMapper extends BaseMapper<CustEX> {

    public int chachongname(@Param("custname")String custname);

    public int chaChongCount(@Param("custname")String custname);

    public int insertOne(CustEX custEX);

    public int chacount();
}

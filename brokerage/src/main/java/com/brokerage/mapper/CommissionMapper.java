package com.brokerage.mapper;

import com.brokerage.entity.Commission;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommissionMapper extends BaseMapper<Commission> {

    public List<Commission> queryOneassnumber(@Param("assetnumber")String assetnumber);
}

package com.brokerage.mapper;

import com.brokerage.entity.Prod;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface ProdInMapper extends BaseMapper<Prod> {
    public Prod queryAll(@Param("id")int id,@Param("depaname")String depaname);
}

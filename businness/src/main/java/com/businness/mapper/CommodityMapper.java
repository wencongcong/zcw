package com.businness.mapper;

import com.businness.entity.Commodity;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    public int insertOne(Commodity commodity);

    public List<Commodity> queryOne(@Param("wid")int wid);
}

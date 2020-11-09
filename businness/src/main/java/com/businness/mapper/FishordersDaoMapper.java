package com.businness.mapper;

import com.businness.entity.FishordersEX;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FishordersDaoMapper extends BaseMapper<FishordersEX> {

    public List<FishordersEX> queryAll(Map map);

    public int insertOneEx(FishordersEX fishordersEX);

    public List<Map<String,String>> fishcount(Map map);
}

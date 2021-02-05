package com.businness.mapper;

import com.businness.entity.ZcdistributorEx;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZcdistributorExMapper extends BaseMapper<ZcdistributorEx> {

    public List<ZcdistributorEx> queryAll(Map map);
}

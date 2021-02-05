package com.brokerage.mapper;

import com.brokerage.entity.Configuration;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfigurationMapper extends BaseMapper<Configuration> {

    //查询全部数据
    public List<Configuration> queryAllConfig();

    //插入
    public int insertOneConfig(Map map);
    //修改
    public int updateConfig(Map map);
    //删除
    public int deleteConfig(@Param("id")int id);
}

package com.brokerage.service;

import com.brokerage.entity.Configuration;
import com.brokerage.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ConfigurationService {
    //查询全部数据
    public Result queryAllConfig();

    //插入
    public Result insertOneConfig(Map map);
    //修改
    public Result updateConfig(Map map);
    //删除
    public Result deleteConfig(int id);
}

package com.brokerage.service.impl;

import com.brokerage.entity.Configuration;
import com.brokerage.mapper.ConfigurationMapper;
import com.brokerage.result.Result;
import com.brokerage.service.ConfigurationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("configurationService")
public class ConfigurationServiceImpl implements ConfigurationService {

    @Resource
    private ConfigurationMapper configurationMapper;

    @Override
    public Result queryAllConfig() {
        List<Configuration>list=configurationMapper.queryAllConfig();
        if (list!=null){
            return Result.success(1,list);
        }else if (list==null){
            return Result.success(0,"查询失败,没有查到数据");
        }else {
            return Result.success(0,"查询失败");
        }
    }

    @Override
    public Result insertOneConfig(Map map) {
        int result= configurationMapper.insertOneConfig(map);
        if (result>0){
            return Result.success(1,"添加成功");
        }else{
            return Result.success(0,"添加失败");
        }
    }

    @Override
    public Result updateConfig(Map map) {
        int result=configurationMapper.updateConfig(map);
        if (result>0){
            return Result.success(1,"添加成功");
        }else{
            return Result.success(0,"添加失败");
        }
    }

    @Override
    public Result deleteConfig(int id) {
        int result=configurationMapper.deleteConfig(id);
        if (result>0){
            return Result.success(1,"添加成功");
        }else{
            return Result.success(0,"添加失败");
        }
    }
}

package com.role.service.impl;

import com.role.entity.Zcprefectural;
import com.role.mapper.ZcprefecturalMapper;
import com.role.result.Result;
import com.role.service.ZcprefecturalService;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("zcprefecturalService")
public class ZcprefecturalServiceImpl implements ZcprefecturalService {

    @Resource
    private ZcprefecturalMapper zcprefecturalMapper;

    @Override
    public List<Zcprefectural> queryAll() {

        return zcprefecturalMapper.queryAll();
    }

    @Override
    public Result insertZcpre(Map map) {
        Zcprefectural zcprefectural=new Zcprefectural();
        if (Integer.parseInt(map.get("pid").toString())==0){
            zcprefectural.setAreaname(map.get("areaname").toString());
            zcprefectural.setPid(0);
            zcprefecturalMapper.insertZcpre(zcprefectural);
            return Result.success(1,"添加省级成功");
        }else if (Integer.parseInt(map.get("pid").toString())>0){
            zcprefectural.setAreaname(map.get("areaname").toString());
            zcprefectural.setPid(Integer.parseInt(map.get("pid").toString()));
            zcprefecturalMapper.insertZcpre(zcprefectural);
            return Result.success(1,"添加市级成功");
        }else {
            return Result.success(0,"添加失败");
        }
    }

    @Override
    public int updateZcpre(Map map) {
        return zcprefecturalMapper.updateZcpre(map);
    }

    @Override
    public int deleteZcpre(int id) {
        return zcprefecturalMapper.deleteZcpre(id);
    }

}

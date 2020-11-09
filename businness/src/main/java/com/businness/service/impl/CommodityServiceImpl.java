package com.businness.service.impl;

import com.businness.entity.Commodity;
import com.businness.mapper.CommodityMapper;
import com.businness.service.CommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public int insertOne(Commodity commodity) {
        return commodityMapper.insertOne(commodity);
    }

    @Override
    public List<Commodity> queryOne(int wid) {
        return commodityMapper.queryOne(wid);
    }
}

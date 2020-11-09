package com.businness.service.impl;

import com.businness.entity.FishordersEX;
import com.businness.mapper.FishordersDaoMapper;
import com.businness.service.FishordersDaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("fishordersDaoService")
public class FishordersDaoServiceImpl implements FishordersDaoService {

    @Resource
    private FishordersDaoMapper fishordersDaoMapper;

    @Override
    public List<FishordersEX> queryAll(Map map) {
        return fishordersDaoMapper.queryAll(map);
    }

    @Override
    public int insertOneEx(FishordersEX fishordersEX) {
        return fishordersDaoMapper.insertOneEx(fishordersEX);
    }

    @Override
    public List<Map<String, String>> fishcount(Map map) {
        return fishordersDaoMapper.fishcount(map);
    }
}

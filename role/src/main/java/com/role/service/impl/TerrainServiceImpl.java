package com.role.service.impl;

import com.role.entity.Terrain;
import com.role.mapper.TerrainMapper;
import com.role.service.TerrainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("terrainService")
public class TerrainServiceImpl implements TerrainService {

    @Resource
    private TerrainMapper terrainMapper;

    @Override
    public List<Terrain> queryAll(String areaname) {
        return terrainMapper.queryAll(areaname);
    }

    @Override
    public int additionTerrain(Terrain terrain) {
        return terrainMapper.additionTerrain(terrain);
    }

    @Override
    public int updateTerrain(Terrain terrain) {
        return terrainMapper.updateTerrain(terrain);
    }
}

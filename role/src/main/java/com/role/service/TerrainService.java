package com.role.service;

import com.role.entity.Terrain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TerrainService {

    public List<Terrain> queryAll(String areaname);

    public int additionTerrain(Terrain terrain);

    public int updateTerrain(Terrain terrain);
}

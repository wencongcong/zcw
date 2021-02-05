package com.role.mapper;

import com.role.entity.Terrain;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TerrainMapper extends BaseMapper<Terrain> {

    public List<Terrain> queryAll(@Param("areaname")String areaname);

    public int additionTerrain(Terrain terrain);

    public int updateTerrain(Terrain terrain);
}

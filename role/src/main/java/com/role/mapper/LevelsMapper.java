package com.role.mapper;

import com.role.entity.Levels;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LevelsMapper extends BaseMapper<Levels> {

    public List<Levels> queryAlllv();

    public int insertOneLevel(Levels levels);

    public int updateOneLevel(Map map);

    public int deleteOneLevel(@Param("id")int id);

    //查询id
    public int chaid(@Param("lname")String lname);
}

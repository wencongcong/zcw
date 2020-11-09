package com.role.service;

import com.role.entity.Levels;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LevelsService {

    public List<Levels> queryAlllv();

    public int insertOneLevel(Levels levels);

    public int updateOneLevel(Map map);

    public int deleteOneLevel(int id);

    public int chaid(String lname);
}

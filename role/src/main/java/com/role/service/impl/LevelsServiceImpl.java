package com.role.service.impl;

import com.role.entity.Levels;
import com.role.mapper.LevelsMapper;
import com.role.service.LevelsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("levelsService")
public class LevelsServiceImpl implements LevelsService {

    @Resource
    private LevelsMapper levelsMapper;

    @Override
    public List<Levels> queryAlllv() {
        return levelsMapper.queryAlllv();
    }

    @Override
    public int insertOneLevel(Levels levels) {
        return levelsMapper.insertOneLevel(levels);
    }

    @Override
    public int updateOneLevel(Map map) {
        return levelsMapper.updateOneLevel(map);
    }

    @Override
    public int deleteOneLevel(int id) {
        return levelsMapper.deleteOneLevel(id);
    }

    @Override
    public int chaid(String lname) {
        return levelsMapper.chaid(lname);
    }
}

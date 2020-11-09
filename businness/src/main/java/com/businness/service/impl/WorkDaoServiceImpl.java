package com.businness.service.impl;

import com.businness.entity.WorkEX;
import com.businness.mapper.WorkDaoMapper;
import com.businness.service.WorkDaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("workDaoService")
public class WorkDaoServiceImpl implements WorkDaoService {

    @Resource
    private WorkDaoMapper workDaoMapper;

    @Override
    public List<WorkEX> queryAll(Map map) {
        return workDaoMapper.queryAll(map);
    }

    @Override
    public int insertOneWork(WorkEX workEX) {
        return workDaoMapper.insertOneWork(workEX);
    }

    @Override
    public int chaworkcount() {
        return workDaoMapper.chaworkcount();
    }

    @Override
    public List<Map<String, String>> workcount(Map map) {
        return workDaoMapper.workcount(map);
    }

    @Override
    public List<Map<String, String>> workchannlcunt(Map map) {
        return workDaoMapper.workchannlcunt(map);
    }

    @Override
    public List<Map> workstatuscunt(Map map) {
        return workDaoMapper.workstatuscunt(map);
    }

    @Override
    public List<Map<String, String>> worksernamecunt(Map map) {
        return workDaoMapper.worksernamecunt(map);
    }

    @Override
    public int workcountsum(Map map) {
        return workDaoMapper.workcountsum(map);
    }
}

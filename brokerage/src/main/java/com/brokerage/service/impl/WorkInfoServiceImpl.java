package com.brokerage.service.impl;

import com.brokerage.entity.Work;
import com.brokerage.mapper.WorkInfoMapper;
import com.brokerage.service.WorkInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("workinfoService")
public class WorkInfoServiceImpl implements WorkInfoService {

    @Resource
    private WorkInfoMapper workInfoMapper;

    @Override
    public Work queryAllstatue(Map map) {
        return workInfoMapper.queryAllstatue(map);
    }

    @Override
    @Transactional
    public int updatesettle(String settlementstatus,String hang,String workid) {
        return workInfoMapper.updatesettle(settlementstatus,hang,workid);
    }

    @Override
    public List<Work> queryAll(Map map) {
        return workInfoMapper.queryAll(map);
    }

    @Override
    public int updatevery(String verify, String workserved, String workid) {
        return workInfoMapper.updatevery(verify, workserved, workid);
    }

    @Override
    public int Autocontrolledwork(Work work) {
        return workInfoMapper.Autocontrolledwork(work);
    }
}

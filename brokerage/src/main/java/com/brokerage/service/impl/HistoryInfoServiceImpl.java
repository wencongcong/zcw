package com.brokerage.service.impl;

import com.brokerage.entity.History;
import com.brokerage.mapper.HistoryInfoMapper;
import com.brokerage.service.HistoryInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("histotryInfoService")
public class HistoryInfoServiceImpl implements HistoryInfoService {

    @Resource
    private HistoryInfoMapper historyInfoMapper;

    @Override
    public int insertOneHistory(History history) {
        return historyInfoMapper.insertOneHistory(history);
    }

    @Override
    public List<History> queryAll(int workid) {
        return historyInfoMapper.queryAll(workid);
    }
}

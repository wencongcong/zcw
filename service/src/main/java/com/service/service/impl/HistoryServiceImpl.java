package com.service.service.impl;


import com.service.entity.History;
import com.service.mapper.HistoryMapper;
import com.service.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public int insertOneHistory(History history) {
        return historyMapper.insertOneHistory(history);
    }

    @Override
    public List<History> queryAll(int workid) {
        return historyMapper.queryAll(workid);
    }
}

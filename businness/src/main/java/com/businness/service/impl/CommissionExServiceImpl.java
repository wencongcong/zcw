package com.businness.service.impl;

import com.businness.entity.CommissionEx;
import com.businness.mapper.CommissionExMapper;
import com.businness.service.CommissionExService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("commissionExService")
public class CommissionExServiceImpl implements CommissionExService {

    @Resource
    private CommissionExMapper commissionExMapper;

    @Override
    public int insertAllComm(CommissionEx commissionEx) {
        return commissionExMapper.insertAllComm(commissionEx);
    }

    @Override
    public List<CommissionEx> queryAllComm(Map map) {
        return commissionExMapper.queryAllComm(map);
    }
}

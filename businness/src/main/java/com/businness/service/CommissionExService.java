package com.businness.service;

import com.businness.entity.CommissionEx;

import java.util.List;
import java.util.Map;

public interface CommissionExService {
    public int insertAllComm(CommissionEx commissionEx);

    public List<CommissionEx> queryAllComm(Map map);
}

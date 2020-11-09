package com.brokerage.service;

import com.brokerage.entity.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryInfoService {
    public int insertOneHistory(History history);

    public List<History> queryAll(int workid);

}

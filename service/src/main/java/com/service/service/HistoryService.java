package com.service.service;

import com.service.entity.History;

import java.util.List;

public interface HistoryService {

    public int insertOneHistory(History history);

    public List<History> queryAll(int workid,int isitright);

    public int insertrighthistory(History history);
}

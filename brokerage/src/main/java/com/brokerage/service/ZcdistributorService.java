package com.brokerage.service;

import com.brokerage.entity.Zcdistributor;
import com.brokerage.result.Result;

import java.util.List;
import java.util.Map;

public interface ZcdistributorService {

    public int insertOneDistributor(Map map);

    public Result queryAllZcdis(Map map);

    public Result reamrk(Map map);
}

package com.brokerage.service;

import com.brokerage.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SettlementAnalusisService {

    public Result queryAll(Map map);

    public Result settlement(Map map);

    public int updateSettStatus(String ordern,String status);
}

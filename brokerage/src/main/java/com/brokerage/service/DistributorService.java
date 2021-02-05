package com.brokerage.service;

import com.brokerage.entity.Distributor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DistributorService {

    public int insertthedistribution(Map map);

    public List<Distributor> queryallinformation(Map map);

    public int deleteDistributor(int id);

    public int updateDistributor(Map map);
}

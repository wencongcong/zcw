package com.businness.service;

import com.businness.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityService {

    public int insertOne(Commodity commodity);

    public List<Commodity> queryOne(int wid);
}

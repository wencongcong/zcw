package com.businness.service;


import com.businness.entity.FishordersEX;

import java.util.List;
import java.util.Map;

public interface FishordersDaoService {

    public List<FishordersEX> queryAll(Map map);

    public int insertOneEx(FishordersEX fishordersEX);

    public List<Map<String,String>> fishcount(Map map);
}

package com.businness.service;


import com.businness.entity.WorkEX;

import java.util.List;
import java.util.Map;

public interface WorkDaoService {

    public List<WorkEX> queryAll(Map map);

    public int insertOneWork(WorkEX workEX);

    public int chaworkcount();

    public List<Map<String,String>> workcount(Map map);

    public List<Map<String ,String>>workchannlcunt(Map map);

    public List<Map>workstatuscunt(Map map);

    public List<Map<String ,String>>worksernamecunt(Map map);

    public int workcountsum(Map map);
}

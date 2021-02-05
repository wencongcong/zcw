package com.businness.mapper;

import com.businness.entity.WorkEX;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkDaoMapper extends BaseMapper<WorkEX> {


    public List<WorkEX> queryAll(Map map);

    public int insertOneWork(WorkEX workEX);

    public int chaworkcount();
    //查询地势
    public List<Map<String,String>> workcount(Map map);
    //查询渠道
    public List<Map<String ,String>>workchannlcunt(Map map);

    public List<Map>workstatuscunt(Map map);

    public List<Map<String ,String>>worksernamecunt(Map map);

    public int workcountsum(Map map);

    public int queryId();

}

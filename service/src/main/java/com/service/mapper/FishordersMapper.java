package com.service.mapper;


import com.service.entity.Fishorders;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FishordersMapper extends BaseMapper<Fishorders> {

    public List<Fishorders> queryAll(Map map);

    public int insertOneFish(Fishorders fishorders);

    public int upstatos(@Param("statos")String statos,@Param("ok")int ok,@Param("id")int id);

    public int chaFid(@Param("wordid") String  wordid);

    public List<Map<String, Object>> grouby(Map map);

    public int uploginno(@Param("servicename")String servicename,@Param("id")int id);
    //查询当天是否是第一次下单
    public int chaOneTian(Map map);
    //信息修改
    public int reamrk(Map map);

    public int insertOne(Fishorders fishorders);

    public int chastatos(@Param("statos")String statos,@Param("id")int id);

    //查询是否已分配
    public int chaOk(@Param("id")int id);
    //查询状态
    public String chaStotus(@Param("id")int id);
    //
    public String chaServicename(@Param("id")int id);
}

package com.service.service;

import com.alibaba.fastjson.JSONObject;
import com.service.entity.Fishorders;
import com.service.result.Result;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface FlyIngService {

    public JSONObject queryFly(String signature_key, String token) throws Exception;

    public Result queryFlyTime() throws ParseException, Exception;

    public int chaFid(String  wordid);

    public List<Fishorders> queryAll(Map map);

    public List<Map<String, Object>> grouby(Map map);

    public int uploginno(String servicename,int id);

    public int insertOneFish(Fishorders fishorders);

    public int chaOneTian(Map map);

    //信息修改
    public int reamrk(Map map);

    public Result sgcha(Map map);

//手动导单
    public int insertOne(Fishorders fishorders);

    public int chastatos(String statos,int id);

    public int upstatos(String statos,int id,String wid,int ok);
    //查询是否已分配
    public int chaOk(int id);
    //查询状态
    public String chaStotus(int id);
    //
    public String chaServicename(@Param("id")int id);

    public Result timetoremind(String servicename);

    public int whether(String phone);

    public List<Fishorders> queryAlls(Map map);

}

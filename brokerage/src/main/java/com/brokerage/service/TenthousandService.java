package com.brokerage.service;

import com.brokerage.entity.Tenthousand;
import com.brokerage.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TenthousandService {

    //查询
    public Result queryAllTen(Map map);
    public Result queryAllTens(Map map);
    //插入
    public Result insertTenth(Map map);
    //修改
    public Result updateTenth(Map map);
    //删除
    public Result deleteTenth(int id);

    public int chastatos(String statos,int id);
    public String chaStotus(int id);
    public String chaServicename(int id);
    public int chaOk(int id);
    public int uploginno(String servicename,int id);
}

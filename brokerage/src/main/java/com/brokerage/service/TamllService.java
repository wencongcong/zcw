package com.brokerage.service;

import com.brokerage.entity.Tmall;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TamllService {
    public List<Tmall> queryAllTmall(Map map);
    public List<Tmall> queryAllTmalls(Map map);

    public int insertOneTmall(Map map);

    public int insertTmallExcel(Tmall tmall);

    public int updatewid(int id, int wid);

    public int updateTmall(Map map);

    public int chastatos(String statos,int id);
    public String chaStotus(int id);
    public String chaServicename(int id);
    public int chaOk(int id);
    public int uploginno(String servicename,int id);
}

package com.service.service;

import com.service.entity.Cust;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CustService {
    public int insertOneCust(Cust cust);

    //查重
    public int chaChong(String cid);

    //查询客户ID是否重复
    public int chawork(String cid);

    //查询数量
    public int chacount();

    public int sgchars(Map map);

    public int chaCustId(String cid);

    public int chachongname(String custname);

    public int chachongcount(String custname);
}

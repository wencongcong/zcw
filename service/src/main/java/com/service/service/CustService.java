package com.service.service;

import com.service.entity.Cust;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CustService {

    //插入
    public int insertOneCust(Cust cust);
    //查重
    public int chaChong(String cid);
    //查询客户ID是否重复
    public int chawork(String cid);
    //查询数量
    public int chacount();
    //查询工单数量
    public int sgchars(Cust cust);
    //查询客户ID
    public int chaCustId(String cid);
    //查询客户是否重复
    public int chachongname(String custname,String custphone,String custidcard);
    //查询重复数量
    public int chachongcount(String custname,String custphone,String custidcard);

    public int changecust(Map map);
}

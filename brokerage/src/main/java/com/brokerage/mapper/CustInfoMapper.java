package com.brokerage.mapper;

import com.brokerage.entity.Cust;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface CustInfoMapper extends BaseMapper<Cust> {


    public int Autocontrolledcust(Cust cust);
    //查重
    public int chaChong(@Param("cid")String cid);
    //查询客户ID是否重复
    public int chawork(@Param("cid") String cid);
    //查询数量
    public int chacount();
    //查询工单数量
    public int sgchars(Map map);
    //查询客户ID
    public int chaCustId(@Param("cid") String cid);
    //查询客户是否重复
    public int chachongname(@Param("custname")String custname,@Param("custphone")String custphone,@Param("custidcard")String custidcard);

    public int queryCid();

    //查询重复数量
    public int chachongcount(@Param("custname")String custname,@Param("custphone")String custphone,@Param("custidcard")String custidcard);
}

package com.service.mapper;

import com.service.entity.Cust;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface CustMapper extends BaseMapper<Cust> {

    public int insertOneCust(Cust cust);

    //查重
    public int chaChong(@Param("cid")String cid);

    //查询客户ID是否重复
    public int chawork(@Param("cid") String cid);

    //查询数量
    public int chacount();

    public int sgchars(Map map);

    public int chaCustId(@Param("cid") String cid);

    public int chachongname(@Param("custname")String custname);

    public int chachongcount(@Param("custname")String custname);
}

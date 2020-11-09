package com.service.service;

import com.service.entity.Orderdetails;
import org.apache.ibatis.annotations.Param;

public interface OrderdetailService {

    //插入信息
    public int insertOne(Orderdetails orders);

    //查询是否重复
    public int chaOne(String orderdetilid);

}

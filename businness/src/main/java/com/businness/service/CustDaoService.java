package com.businness.service;

import com.businness.entity.CustEX;

public interface CustDaoService {

    public int chachongname(String custname,String custphone);

    public int insertOne(CustEX custEX);

    public int chacount();

    public int chaChongCount(String custname,String custphone);

    public int queryCid();
}

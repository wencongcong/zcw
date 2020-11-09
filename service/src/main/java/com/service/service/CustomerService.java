package com.service.service;

import com.service.entity.CustomerInfo;

public interface CustomerService {

    public int insertOne(CustomerInfo customer);

    public int chaChong(String custmerNo);
}

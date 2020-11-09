package com.businness.service;


import com.businness.entity.ProdEX;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProdDaoService {

    public List<ProdEX> queryAll(Map map);

    public int chaAcceptName(String productsName,String depaname);
}

package com.businness.service.impl;

import com.businness.entity.ProdEX;
import com.businness.mapper.ProdDaoMapper;
import com.businness.service.ProdDaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("prodDaoService")
public class ProdDaoServiceImpl implements ProdDaoService {

    @Resource
    private ProdDaoMapper prodDaoMapper;

    @Override
    public List<ProdEX> queryAll(Map map) {
        return prodDaoMapper.queryAll(map);
    }

    @Override
    public int chaAcceptName(String productsName,String depaname) {
        return prodDaoMapper.chaAcceptName(productsName,depaname);
    }
}

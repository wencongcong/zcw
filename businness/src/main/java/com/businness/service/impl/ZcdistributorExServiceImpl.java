package com.businness.service.impl;

import com.businness.entity.ZcdistributorEx;
import com.businness.mapper.ZcdistributorExMapper;
import com.businness.service.ZcdistributorExService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("zcdistributorExService")
public class ZcdistributorExServiceImpl implements ZcdistributorExService {

    @Resource
    private ZcdistributorExMapper zcdistributorExMapper;

    @Override
    public List<ZcdistributorEx> queryAll(Map map) {
        return zcdistributorExMapper.queryAll(map);
    }
}

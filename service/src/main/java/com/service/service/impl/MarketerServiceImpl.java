package com.service.service.impl;

import com.service.entity.MarketerInfo;
import com.service.mapper.MarketerMapper;
import com.service.service.MarketerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("marketerService")
public class MarketerServiceImpl implements MarketerService {

    @Resource
    private MarketerMapper marketerMapper;

    @Override
    @Transactional
    public int insertOne(MarketerInfo mark) {
        int result=marketerMapper.insertOne(mark);
        return result;
    }

    @Override
    public int chaChong(String createNo) {
        int result=marketerMapper.chaChong(createNo);
        return result;
    }
}

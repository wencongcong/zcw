package com.service.service.impl;

import com.service.entity.Stream;
import com.service.mapper.StreamMapper;
import com.service.service.StreamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("streamService")
public class StreamServiceImpl implements StreamService {


    @Resource
    private StreamMapper streamMapper;

    @Override
    public List<Stream> queryAll(Map map) {
        return streamMapper.queryAll(map);
    }

    @Override
    public int insertStream(Stream stream) {
        return streamMapper.insertStream(stream);
    }

    @Override
    public int deleteStream(int id) {
        return streamMapper.deleteStream(id);
    }

    @Override
    public int updateStream(Map map) {
        return streamMapper.updateStream(map);
    }

    @Override
    public Stream queryOneStream(int id) {
        return streamMapper.queryOneStream(id);
    }
}

package com.role.service.impl;

import com.role.entity.Channel;
import com.role.mapper.ChannelMapper;
import com.role.service.ChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public List<Channel> queryAll() {
        return channelMapper.queryAll();
    }

    @Override
    public int insertChannel(Map map) {
        return channelMapper.insertChannel(map);
    }

    @Override
    public int updateChannel(Map map) {
        return channelMapper.updateChannel(map);
    }

    @Override
    public int deleteChaneel(int id) {
        return channelMapper.deleteChaneel(id);
    }
}

package com.role.service;

import com.role.entity.Channel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChannelService {

    public List<Channel> queryAll();

    public int insertChannel(Map map);

    public int updateChannel(Map map);

    public int deleteChaneel(int id);
}

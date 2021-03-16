package com.role.mapper;


import com.role.entity.Channel;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChannelMapper extends BaseMapper<Channel> {

    public List<Channel> queryAll();

    public int insertChannel(Map map);

    public int updateChannel(Map map);

    public int deleteChaneel(@Param("id")int id);
}

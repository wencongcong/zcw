package com.businness.mapper;

import com.businness.entity.WebSockets;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WebSocketsMapper extends BaseMapper<WebSockets> {

    public int insertOneWeb(WebSockets webSockets);

    public List<WebSockets> queryAllWeb(Map map);


}

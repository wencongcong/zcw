package com.businness.service.impl;

import com.businness.entity.WebSockets;
import com.businness.mapper.WebSocketsMapper;
import com.businness.service.WebSocketsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("webSockets")
public class WebSocketsServiceImpl implements WebSocketsService {

    @Resource
    private WebSocketsMapper webSocketsMapper;

    @Override
    public int insertOneWeb(WebSockets webSockets) {
        return webSocketsMapper.insertOneWeb(webSockets);
    }

    @Override
    public List<WebSockets> queryAllWeb(Map map) {
        return webSocketsMapper.queryAllWeb(map);
    }
}

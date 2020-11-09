package com.businness.service;

import com.businness.entity.WebSockets;

import java.util.List;
import java.util.Map;

public interface WebSocketsService {


    public int insertOneWeb(WebSockets webSockets);

    public List<WebSockets> queryAllWeb(Map map);
}

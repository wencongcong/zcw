package com.service.service;

import com.service.entity.Stream;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StreamService {

    public List<Stream> queryAll(Map map);

    public int insertStream(Stream stream);

    public int deleteStream(int id);

    public int updateStream(Map map);

    public Stream queryOneStream(int id);
}

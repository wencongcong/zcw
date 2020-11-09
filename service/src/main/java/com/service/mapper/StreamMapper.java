package com.service.mapper;

import com.service.entity.Stream;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StreamMapper extends BaseMapper<Stream> {

    public List<Stream> queryAll(Map map);

    public int insertStream(Stream stream);

    public int deleteStream(@Param("id") int id);

    public int updateStream(Map map);

    public Stream queryOneStream(@Param("id")int id);
}

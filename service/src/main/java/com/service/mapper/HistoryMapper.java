package com.service.mapper;

import com.service.entity.History;
import com.service.entity.OrderHstory;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
    public int insertOneHistory(History history);

    public List<History> queryAll(@Param("workid")int workid);

    public int insertrighthistory(History history);
 }

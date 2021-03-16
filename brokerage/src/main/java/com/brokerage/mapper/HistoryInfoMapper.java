package com.brokerage.mapper;

import com.brokerage.entity.History;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryInfoMapper extends BaseMapper<History> {

    public int insertOneHistory(History history);

    public List<History> queryAll(@Param("workid")int workid,@Param("isitright")int isitright);
}

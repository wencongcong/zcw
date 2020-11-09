package com.service.mapper;

import com.service.entity.MarketerInfo;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketerMapper extends BaseMapper<MarketerInfo> {
    public int insertOne(MarketerInfo mark);

    public int chaChong(@Param("createNo")String createNo);
}

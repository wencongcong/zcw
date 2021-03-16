package com.role.mapper;

import com.role.entity.Zcprefectural;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZcprefecturalMapper extends BaseMapper<Zcprefectural> {


    public int insertZcpre(Zcprefectural zcprefectural);

    public List<Zcprefectural> queryAll();

    public List<Zcprefectural> queryAlls();

    public int updateZcpre(Map map);

    public int deleteZcpre(@Param("id")int id);

}

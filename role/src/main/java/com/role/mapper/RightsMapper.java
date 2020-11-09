package com.role.mapper;

import com.role.entity.Rights;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RightsMapper extends BaseMapper<Rights> {

    public List<Rights> queryAllr();

    public int insertOneRight(Rights rights);

    public int updateOneRight(Map map);

    public int deleteOneRight(@Param("id")int id);
}

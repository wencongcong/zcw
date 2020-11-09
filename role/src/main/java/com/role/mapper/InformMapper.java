package com.role.mapper;

import com.role.entity.Inform;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InformMapper extends BaseMapper<Inform> {

    public List<Inform> querAll(Map map);

    public int insertOneInform(Inform inform);

    public int updateOneInform(Map map);

    public int deleteOneInform(int id);
}

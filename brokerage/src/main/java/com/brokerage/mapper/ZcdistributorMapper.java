package com.brokerage.mapper;

import com.brokerage.entity.Zcdistributor;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZcdistributorMapper extends BaseMapper<Zcdistributor> {

    public int insertOneDistributor(Map map);

    public List<Zcdistributor> queryAllZcdis(Map map);

    public int reamrk(Map map);

    public int updatewid(@Param("id")int id,@Param("wid")int wid);
}

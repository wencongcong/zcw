package com.brokerage.mapper;

import com.brokerage.entity.Tmall;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TmallMapper extends BaseMapper<Tmall> {

    public List<Tmall> queryAllTmall(Map map);
    public List<Tmall> queryAllTmalls(Map map);

    public int insertOneTmall(Map map);

    public int insertTmallExcel(Tmall tmall);

    public int updatewid(@Param("id")int id,@Param("wid")int wid);

    public int updateTmall(Map map);

    public int chastatos(@Param("statos")String statos, @Param("id")int id);
    public String chaStotus(@Param("id")int id);
    public String chaServicename(@Param("id")int id);
    public int chaOk(@Param("id")int id);
    public int uploginno(@Param("servicename")String servicename,@Param("id")int id);

}

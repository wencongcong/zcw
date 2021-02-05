package com.brokerage.mapper;

import com.brokerage.entity.Tenthousand;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TenthousandMapper extends BaseMapper<Tenthousand> {

    //查询
    public List<Tenthousand> queryAllTen(Map map);
    //查询
    public List<Tenthousand> queryAllTens(Map map);
    //插入
    public int insertTenth(Map map);
    //修改
    public int updateTenth(Map map);
    //删除
    public int deleteTenth(@Param("id")int id);

    public int updatewid(@Param("id")int id,@Param("wid")int wid);

    public int chastatos(@Param("statos")String statos,@Param("id")int id);
    public String chaStotus(@Param("id")int id);
    public String chaServicename(@Param("id")int id);
    public int chaOk(@Param("id")int id);
    public int uploginno(@Param("servicename")String servicename,@Param("id")int id);

}

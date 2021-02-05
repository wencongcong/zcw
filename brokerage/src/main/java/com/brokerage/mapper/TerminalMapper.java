package com.brokerage.mapper;

import com.brokerage.entity.Terminal;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TerminalMapper extends BaseMapper<Terminal> {

    public List<Terminal> queryAllTerminal(Map map);
    public List<Terminal> queryAllTerminals(Map map);

    public int insertOneTerminal(Map map);
    public int insertTerminal(Terminal terminal);

    public int updateTerminal(Map map);

    public int updatewid(@Param("id")int id,@Param("wid")int wid);

    public int chastatos(@Param("statos")String statos, @Param("id")int id);
    public String chaStotus(@Param("id")int id);
    public String chaServicename(@Param("id")int id);
    public int chaOk(@Param("id")int id);
    public int uploginno(@Param("servicename")String servicename,@Param("id")int id);



}

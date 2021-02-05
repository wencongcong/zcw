package com.brokerage.service;

import com.brokerage.entity.Terminal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TerminalService {

    public List<Terminal> queryAllTerminal(Map map);
    public List<Terminal> queryAllTerminals(Map map);

    public int insertOneTerminal(Map map);
    public int insertTerminal(Terminal terminal);

    public int updateTerminal(Map map);

    public int updatewid(int id, int wid);

    public int chastatos(String statos,int id);
    public String chaStotus(int id);
    public String chaServicename(int id);
    public int chaOk(int id);
    public int uploginno(String servicename,int id);
}

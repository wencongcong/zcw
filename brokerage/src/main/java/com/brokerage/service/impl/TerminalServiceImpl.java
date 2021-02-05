package com.brokerage.service.impl;

import com.brokerage.entity.Terminal;
import com.brokerage.mapper.TerminalMapper;
import com.brokerage.service.TerminalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("terminalService")
public class TerminalServiceImpl implements TerminalService {

    @Resource
    private TerminalMapper terminalMapper;

    @Override
    public List<Terminal> queryAllTerminal(Map map) {
        return terminalMapper.queryAllTerminal(map);
    }

    @Override
    public List<Terminal> queryAllTerminals(Map map) {
        return terminalMapper.queryAllTerminals(map);
    }

    @Override
    public int insertOneTerminal(Map map) {
        return terminalMapper.insertOneTerminal(map);
    }

    @Override
    public int insertTerminal(Terminal terminal) {
        return terminalMapper.insertTerminal(terminal);
    }

    @Override
    public int updateTerminal(Map map) {
        return terminalMapper.updateTerminal(map);
    }

    @Override
    public int updatewid(int id, int wid) {
        return terminalMapper.updatewid(id, wid);
    }

    @Override
    public int chastatos(String statos, int id) {
        return terminalMapper.chastatos(statos, id);
    }

    @Override
    public String chaStotus(int id) {
        return terminalMapper.chaStotus(id);
    }

    @Override
    public String chaServicename(int id) {
        return terminalMapper.chaServicename(id);
    }

    @Override
    public int chaOk(int id) {
        return terminalMapper.chaOk(id);
    }

    @Override
    public int uploginno(String servicename, int id) {
        return terminalMapper.uploginno(servicename, id);
    }
}

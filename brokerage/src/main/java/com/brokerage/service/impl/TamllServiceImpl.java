package com.brokerage.service.impl;

import com.brokerage.entity.Tmall;
import com.brokerage.mapper.TmallMapper;
import com.brokerage.service.TamllService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("tmallService")
public class TamllServiceImpl implements TamllService {

    @Resource
    private TmallMapper tmallMapper;

    @Override
    public List<Tmall> queryAllTmall(Map map) {
        return tmallMapper.queryAllTmall(map);
    }

    @Override
    public List<Tmall> queryAllTmalls(Map map) {
        return tmallMapper.queryAllTmalls(map);
    }

    @Override
    public int insertOneTmall(Map map) {

        return tmallMapper.insertOneTmall(map);
    }

    @Override
    public int insertTmallExcel(Tmall tmall) {
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tmall.setCreatetime(sfs.format(new Date()));
        return tmallMapper.insertTmallExcel(tmall);
    }

    @Override
    public int updatewid(int id, int wid) {
        return tmallMapper.updatewid(id, wid);
    }

    @Override
    public int updateTmall(Map map) {
        return tmallMapper.updateTmall(map);
    }

    @Override
    public int chastatos(String statos, int id) {
        return tmallMapper.chastatos(statos, id);
    }

    @Override
    public String chaStotus(int id) {
        return tmallMapper.chaStotus(id);
    }

    @Override
    public String chaServicename(int id) {
        return tmallMapper.chaServicename(id);
    }

    @Override
    public int chaOk(int id) {
        return tmallMapper.chaOk(id);
    }

    @Override
    public int uploginno(String servicename, int id) {
        return tmallMapper.uploginno(servicename, id);
    }
}

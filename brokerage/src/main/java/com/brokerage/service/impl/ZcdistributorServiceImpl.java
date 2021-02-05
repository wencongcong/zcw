package com.brokerage.service.impl;

import com.brokerage.entity.Zcdistributor;
import com.brokerage.mapper.ZcdistributorMapper;
import com.brokerage.result.Result;
import com.brokerage.service.ZcdistributorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("zcdistributorService")
public class ZcdistributorServiceImpl implements ZcdistributorService {

    @Resource
    private ZcdistributorMapper zcdistributorMapper;

    @Override
    public int insertOneDistributor(Map map) {
        Date date=new Date();
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        map.put("ordertime",sj);
        return zcdistributorMapper.insertOneDistributor(map);
    }

    @Override
    public Result queryAllZcdis(Map map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Zcdistributor> works= zcdistributorMapper.queryAllZcdis(map);
        PageInfo<Zcdistributor> pageInfo = new PageInfo(works);
        return Result.success(1,pageInfo);
    }

    @Override
    public Result reamrk(Map map) {
        return Result.success(1,zcdistributorMapper.reamrk(map));
    }
}

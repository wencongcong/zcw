package com.service.statement.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.domeVo.FishordVo;
import com.service.domeVo.FishorderratioVo;
import com.service.mapper.FishordersMapper;
import com.service.mapper.StatementMapper;
import com.service.mapper.WorkMapper;
import com.service.result.Result;
import com.service.statement.StatementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("statementService")
public class StatementServiceImpl implements StatementService {

    @Resource
    private StatementMapper statementMapper;

    @Override
    public FishordVo queryAll(Map map) {
        return statementMapper.queryAll(map);
    }

    @Override
    public FishorderratioVo queryAllRatio(Map map) {
        FishordVo fishordVo1=statementMapper.queryAll(map);//获取区间1
        FishordVo fishordVo2=statementMapper.queryAllvs(map);//获取区间2
        FishorderratioVo fishorderratioVo=new FishorderratioVo();
        //只计算没有百分比的数值，写死长度为6
            fishorderratioVo.setAcceptnumlv(String.valueOf((Integer.parseInt(fishordVo1.getAcceptnum())-Integer.parseInt(fishordVo2.getAcceptnum()))/Integer.parseInt(fishordVo2.getAcceptnum())));
            fishorderratioVo.setBusinessparameterslv(String.valueOf((Integer.parseInt(fishordVo1.getBusinessparameters())-Integer.parseInt(fishordVo2.getBusinessparameters()))/Integer.parseInt(fishordVo2.getBusinessparameters())));
            fishorderratioVo.setChargebacknumlv(String.valueOf((Integer.parseInt(fishordVo1.getChargebacknum())-Integer.parseInt(fishordVo2.getChargebacknum()))/Integer.parseInt(fishordVo2.getChargebacknum())));
            fishorderratioVo.setPigeonholenumlv(String.valueOf((Integer.parseInt(fishordVo1.getPigeonholenum())-Integer.parseInt(fishordVo2.getPigeonholenum()))/Integer.parseInt(fishordVo2.getPigeonholenum())));
            fishorderratioVo.setSubmitnumlv(String.valueOf((Integer.parseInt(fishordVo1.getSubmitnum())-Integer.parseInt(fishordVo2.getSubmitnum()))/Integer.parseInt(fishordVo2.getSubmitnum())));
            fishorderratioVo.setWorkingoddlv(String.valueOf((Integer.parseInt(fishordVo1.getWorkingodd())-Integer.parseInt(fishordVo2.getWorkingodd()))/Integer.parseInt(fishordVo2.getWorkingodd())));
        return fishorderratioVo;
    }

    @Override
    public List<Map<String, String>> queryAddressCount(Map map) {
        return statementMapper.queryAddressCount(map);
    }

    @Override
    public PageInfo<FishorderratioVo> queryProd(Map map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<FishorderratioVo> lists=new ArrayList<>();
        List<Map<String,String>> lsit=statementMapper.queryProd(map);
        int count=statementMapper.queryFiCount(map);
        int total=0;
        for (Map map1:lsit){
            FishorderratioVo fishorderratioVo=new FishorderratioVo();
            fishorderratioVo.setAccept(map1.get("accept").toString());
            fishorderratioVo.setAcceptcount(map1.get("acceptcount").toString());
            fishorderratioVo.setAcceptzb(String.valueOf(Integer.parseInt(map1.get("acceptcount").toString())/count*100));
            lists.add(fishorderratioVo);
            total=Integer.parseInt(map1.get("acceptcounts").toString());
        }

        PageInfo<FishorderratioVo> pageInfo = new PageInfo(lists);
        pageInfo.setNextPage(count);
        pageInfo.setTotal(total);
        return pageInfo;
    }

    @Override
    public List<FishordVo> queryService(Map map) {
        return statementMapper.queryService(map);
    }

    @Override
    public List<Map<String, String>> queryQuTu(Map map) {
        return statementMapper.queryQuTu(map);
    }


}

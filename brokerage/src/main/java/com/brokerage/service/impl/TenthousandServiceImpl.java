package com.brokerage.service.impl;

import com.brokerage.entity.Tenthousand;
import com.brokerage.entity.Tmall;
import com.brokerage.mapper.TenthousandMapper;
import com.brokerage.result.Result;
import com.brokerage.service.TenthousandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("tenthousandService")
public class TenthousandServiceImpl implements TenthousandService {

    @Resource
    private TenthousandMapper tenthousandMapper;


    @Override
    public Result queryAllTen(Map map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Tenthousand> list=tenthousandMapper.queryAllTen(map);
        PageInfo<Tmall> pageInfos = new PageInfo(list);
        if (pageInfos!=null){
            return Result.success(1,pageInfos);
        }else if (pageInfos==null){
            return Result.success(0,"查询失败,没有查到数据");
        }else {
            return Result.success(0,"查询失败");
        }
    }

    @Override
    public Result queryAllTens(Map map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Tenthousand> list=tenthousandMapper.queryAllTens(map);
        PageInfo<Tmall> pageInfos = new PageInfo(list);
        if (pageInfos!=null){
            return Result.success(1,pageInfos);
        }else if (pageInfos==null){
            return Result.success(0,"查询失败,没有查到数据");
        }else {
            return Result.success(0,"查询失败");
        }
    }

    @Override
    public Result insertTenth(Map map) {
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("createtime",sfs.format(new Date()));
        int result= tenthousandMapper.insertTenth(map);
        if (result>0){
            return Result.success(1,"添加成功");
        }else{
            return Result.success(0,"添加失败");
        }
    }

    @Override
    public Result updateTenth(Map map) {
        int result= tenthousandMapper.updateTenth(map);
        if (result>0){
            return Result.success(1,"修改成功");
        }else{
            return Result.success(0,"修改成功");
        }
    }

    @Override
    public Result deleteTenth(int id) {
        int result= tenthousandMapper.deleteTenth(id);
        if (result>0){
            return Result.success(1,"修改成功");
        }else{
            return Result.success(0,"修改成功");
        }
    }

    @Override
    public int chastatos(String statos, int id) {
        return tenthousandMapper.chastatos(statos, id);
    }

    @Override
    public String chaStotus(int id) {
        return tenthousandMapper.chaStotus(id);
    }

    @Override
    public String chaServicename(int id) {
        return tenthousandMapper.chaServicename(id);
    }

    @Override
    public int chaOk(int id) {
        return tenthousandMapper.chaOk(id);
    }

    @Override
    public int uploginno(String servicename, int id) {
        return tenthousandMapper.uploginno(servicename, id);
    }
}

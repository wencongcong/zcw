package com.service.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.entity.Fishorders;
import com.service.entity.History;
import com.service.entity.Stream;
import com.service.result.Result;
import com.service.service.FlyIngService;
import com.service.service.HistoryService;
import com.service.service.StreamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/fley")
public class OpporController {

    @Resource
    private FlyIngService flyIngService;
    @Resource
    private HistoryService historyService;
    @Resource
    private StreamService streamService;


    @RequestMapping(value = "/FlyIng",method = RequestMethod.GET)
    public Result FlyIng()throws Exception{
        return flyIngService.queryFlyTime();
    }
    @RequestMapping(value = "/queryFi",method = RequestMethod.POST)
    public Result queryAll(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));//这行是重点，表示从pageNum页开始，每页pageSize条数据
        if (Integer.parseInt(map.get("desc").toString())==0){
            PageHelper.orderBy("trackingtime desc");
        }else if(Integer.parseInt(map.get("desc").toString())==1){
            PageHelper.orderBy("trackingtime asc");
        }else if(Integer.parseInt(map.get("desc").toString())==2){
            PageHelper.orderBy("ordertime desc");
        }
        List<Fishorders> list= flyIngService.queryAll(map);
        PageInfo<Fishorders> pageInfo = new PageInfo(list);
        return Result.success(1,pageInfo);
    }

    @RequestMapping(value = "/grouby",method = RequestMethod.POST)
    public Map zd(@RequestParam Map map){
        Map maps=new HashMap();
        maps.put("grouby",flyIngService.grouby(map));
        return maps;
    }

    @RequestMapping(value = "/insertOneFi",method = RequestMethod.POST)
    public Result insertOne(@RequestParam Map map){
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);
        Fishorders fishorders=new Fishorders();
        fishorders.setChannel(map.get("channel").toString());
        fishorders.setPhone(map.get("phone").toString());
        fishorders.setName(map.get("name").toString());
        fishorders.setRemark(map.get("remark").toString());
        fishorders.setNameofadvertiser(map.get("nameofadvertiser").toString());
        fishorders.setIdcard(map.get("idcard").toString());
        fishorders.setInterior(map.get("interior").toString());
        fishorders.setOrdertime(sj);
        fishorders.setBroadband(map.get("broadband").toString());
        int result= flyIngService.insertOne(fishorders);
        if (result>0){
            return Result.success(1,"导入成功");
        }else {
            return Result.success(0,"导入失败");
        }
    }

    @RequestMapping(value = "/xiuuplogin",method = RequestMethod.POST)
    public Result xiuuplogin(@RequestParam Map map) throws Exception{
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject data= JSON.parseObject(map.get("data").toString().trim());
        JSONObject datas= data.getJSONObject("data");
        int length=Integer.parseInt(data.get("length").toString());
        String servicename=data.get("servicename").toString();
        if (length <=10) {
            for (int i = 0; i < length; i++) {
                String uplogin = datas.get(i).toString();
                int id = Integer.parseInt(uplogin);
                int resul = flyIngService.chastatos("营销成功", id);
                if (resul == 1) {
                    return Result.fail(0, "营销成功不能分配,商机单编号为:"+id);
                } else if(flyIngService.chaOk(id)==1){
                    return Result.fail(0, "已分配过的不能分配,商机单编号为:"+id);
                }else{
                    if (uplogin != null || uplogin != "") {
                        int result = flyIngService.uploginno(servicename, id);
                        if (result > 0) {
                            continue;
                        } else {
                            return Result.success(0, "分配失败");
                        }
                    }
                }
            }
        } else {
            return Result.success(0, "一次分配不能超过10条");
        }
        return Result.success(1, "运行成功");
    }

    @RequestMapping(value = "/baidu",method = RequestMethod.POST)
    public Result baidu(@RequestParam Map map){
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
        String sj=sfs.format(date);
        String tj=sfd.format(date);
        Fishorders fishorders=new Fishorders();
        Map maps=new HashMap();
        maps.put("channel","信息流_百度");
        maps.put("phone",map.get("phone").toString());
        maps.put("ordertime",tj);
        int result=flyIngService.chaOneTian(maps);
        if (result>0){
            return Result.success(0,"当天不能连续下单");
        }else {
            fishorders.setName(map.get("username").toString());
            fishorders.setPhone(map.get("phone").toString());
            fishorders.setInterior(map.get("address").toString());
            fishorders.setRemark(map.get("source").toString());
            fishorders.setAddress(map.get("area").toString());
            fishorders.setNameofadvertiser(map.get("company").toString());
            fishorders.setToproomotelinks( map.get("url").toString());
            fishorders.setChannel("信息流_百度");
            fishorders.setStatos("待外呼");
            fishorders.setOrdertime(sj);
        }
        int rs=flyIngService.insertOneFish(fishorders);
        if (rs>0){
            return Result.success(1,"提交成功");
        }else{
            return Result.success(0,"提交失败");
        }
    }

    @RequestMapping(value = "/transferorder",method = RequestMethod.POST)
    public Result transferorder(@RequestParam Map map){
            return flyIngService.sgcha(map);
    }

    @RequestMapping(value = "/xiugai",method = RequestMethod.POST)
    public Result xiugai(@RequestParam Map map){
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);
        map.put("uplogintime",sj);
        int result=flyIngService.reamrk(map);
        if (result>0){
            return Result.success(1,"修改成功");
        }else {
            return Result.success(0,"修改失败");
        }
    }

    @RequestMapping(value = "/liu",method = RequestMethod.POST)
    public Result liu(@RequestParam Map map){
       List<Stream> list=streamService.queryAll(map);
        return Result.success(1,list);
    }

    @RequestMapping(value = "/insertliu",method = RequestMethod.POST)
    public Result insertliu(@RequestBody Map map){
        Stream stream=new Stream();
        stream.setFlowName(map.get("flowName").toString());
        stream.setStateName(map.get("stateName").toString());
        int result=streamService.insertStream(stream);
        if (result>0){
            return Result.success(1,"添加成功");
        }else {
            return Result.success(0,"添加失败");
        }
    }
    @RequestMapping(value = "/updateliu",method = RequestMethod.POST)
    public Result updateliu(@RequestParam Map map){
        int result=streamService.updateStream(map);
        if (result>0){
            return Result.success(1,"修改成功");
        }else {
            return Result.success(0,"修改失败");
        }
    }
    @RequestMapping(value = "/deleteliu",method = RequestMethod.POST)
    public Result deleteliu(@RequestParam(value = "id",defaultValue = "0")int id){
        int result=streamService.deleteStream(id);
        if (result>0){
            return Result.success(1,"删除成功");
        }else {
            return Result.success(0,"删除失败");
        }
    }
}

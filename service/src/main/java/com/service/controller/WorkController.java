package com.service.controller;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.entity.History;
import com.service.entity.OrderHstory;
import com.service.entity.Stream;
import com.service.entity.Work;
import com.service.result.Result;
import com.service.service.HistoryService;
import com.service.service.OrderInfoService;
import com.service.service.StreamService;
import com.service.service.WorkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/work")
public class WorkController {

    @Resource
    private WorkService workService;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private HistoryService historyService;
    @Resource
    private StreamService streamService;


    @RequestMapping(value = "/cha",method = RequestMethod.POST)
    public Result queryAll(@RequestBody Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        PageHelper.orderBy("reminder desc");
        List<Work> works= workService.queryAll(map);
        PageInfo<Work> pageInfo = new PageInfo(works);
        return Result.success(1,pageInfo);
    }

    @RequestMapping(value = "/goury",method = RequestMethod.POST)
    public Map group(@RequestParam Map map){
        Map<Object,Object> maps=new HashMap<>();
        maps.put("grouy",workService.groupCount(map));
        return maps;
    }

    @RequestMapping(value = "/reminder",method = RequestMethod.POST)
    public Result reminder(@RequestParam(defaultValue = "0",value = "reminder")int reminder,@RequestParam(defaultValue = "0",value = "workid")int workid){
        int result=workService.Reminder(reminder, workid);
        if (result>0){
            return Result.success(1,"催单成功");
        }else {
            return Result.fail(0,"催单失败");
        }
    }

    //修改状态
    @RequestMapping(value = "/remark",method = RequestMethod.POST)
    public Result remark(@RequestParam Map map) throws NullPointerException{
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        History history=new History();
        String sj=sfs.format(date);
        String statos=map.get("statos").toString();
        history.setSevenstatus(statos);
        history.setWorkid(Integer.parseInt(map.get("workid").toString()));
        history.setUplogintime(sj);
        history.setStatosname(map.get("statosname").toString());
        history.setUplognno(map.get("servicename").toString()+"-"+map.get("oldname").toString());
        history.setState(map.get("status").toString());
        history.setHistorys(map.get("historys").toString());
        switch (statos){
            case "废弃单":
                if (workService.chaorderno(Integer.parseInt(map.get("orderId").toString()))<=0||statos.equals("已撤单")||statos.equals("已取消")){
                    workService.Updateremark(map);
                }else if (workService.chaorderno(Integer.parseInt(map.get("orderId").toString()))>0||statos.equals("成功订单")){
                    workService.Updateremark(map);
                }else {
                    return Result.fail("不是已撤单/已取消订单,不可废弃");
                }
                break;
            case "取消办理":
                if(workService.chaorderno(Integer.parseInt(map.get("orderId").toString()))<=0||statos.equals("已取消")||statos.equals("已撤单")){
                    workService.Updateremark(map);
                }else{
                    return Result.fail("不能取消办理");
                }
                break;
        }
        historyService.insertOneHistory(history);
        map.put("historyid",history.getId());
        if (workService.Updateremark(map)>0) {
            return Result.success(1, "成功");
        }

        return Result.fail(0, "失败");
    }

    @RequestMapping(value = "/historyqueryll",method = RequestMethod.POST)
    public Result history(@RequestParam String id){
        List<History>lists= historyService.queryAll(Integer.parseInt(id));
        return Result.success(1,lists);
    }

    @RequestMapping(value = "/changeuploginno",method = RequestMethod.POST)
    @ResponseBody
    public Result uploginno(@RequestParam Map map){
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map maps=new HashMap();
        JSONObject data= JSON.parseObject(map.get("data").toString().trim());
        int length=Integer.parseInt(data.get("length").toString());
        JSONObject datad=data.getJSONObject("data");
        for (int i=0;i<length;i++){
            JSONObject datas=datad.getJSONObject(String.valueOf(i));
            String workid=datas.getString("workid");
            String status=datas.getString("status");
            String uploginno=datas.getString("uploginno");
            String curentname=datas.getString("curentname");
            String oldname=datas.getString("oldname");
            if (status.equals("待受理分配")){
                maps.put("assigneeName",curentname);
                maps.put("status","待受理");
                maps.put("workid",workid);
              int result= workService.updateassign(maps);
                if (result>0){
                    History history=new History();
                    String sj=sfs.format(date);
                    history.setWorkid(Integer.parseInt(workid));
                    history.setUplogintime(sj);
                    history.setUplognno(uploginno+"-"+oldname);
                    history.setCurentname(curentname);
                    history.setStatosname(status);
                    history.setState("待受理");
                    history.setSevenstatus("");
                    history.setHistorys("");
                    historyService.insertOneHistory(history);
                    continue;
                }else{
                    return Result.fail(0,"分配失败");
                }
            }else{
                maps.put("assigneeName",curentname);
                maps.put("status",null);
                maps.put("workid",workid);
                int result= workService.updateassign(maps);
                if (result>0){
                    History history=new History();
                    String sj=sfs.format(date);
                    history.setWorkid(Integer.parseInt(workid));
                    history.setUplogintime(sj);
                    history.setUplognno(uploginno+"-"+oldname);
                    history.setCurentname(curentname);
                    history.setStatosname(status);
                    history.setState(status);
                    history.setHistorys("");
                    history.setSevenstatus("");
                    historyService.insertOneHistory(history);
                    continue;
                }else{
                    return Result.fail(0,"分配失败");
                }
            }
        }
        return Result.success(1,"分配成功");
    }

    @RequestMapping(value = "/remarks",method = RequestMethod.POST)
    public Result updateremark(@RequestParam Map map){
        int result=workService.uodateRemark(map.get("remark").toString(),map.get("workid").toString());
        if (result>0){
            return Result.success(1,"修改成功");
        }else {
            return Result.fail(0,"修改失败");
        }
    }

    @RequestMapping(value = "/chaliu",method = RequestMethod.POST)
    public Result chaliu(@RequestParam(value = "id",defaultValue = "0")int id){
        Stream stream=streamService.queryOneStream(id);
        if (stream==null){
            return Result.fail(0,"查询失败");
        }else {
            return Result.success(1,stream);
        }
    }
}

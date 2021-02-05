package com.service.controller;

import com.service.entity.Fishorders;
import com.service.result.Result;
import com.service.service.FlyIngService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/fly")
public class FlyController {

    @Resource
    private FlyIngService flyIngService;

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
            fishorders.setServicename("");
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

    @RequestMapping(value = "/rtbuildingblocksoffish",method = RequestMethod.POST)
    @ResponseBody
    public Result baidujmyyt(@RequestBody Map map){
        Map maps=new HashMap();
        maps.put("data",map);
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pst=map.toString();
        String pst1=pst.substring(0,pst.length());
        String [] temp=pst1.split(", ");
        String sts=temp[0].substring(temp[0].lastIndexOf("=")+1);
        String sts1=temp[1].substring(temp[1].lastIndexOf("=")+1);
        String sts2=temp[2].substring(temp[2].lastIndexOf("=")+1);
        String sts3=temp[3].substring(temp[3].lastIndexOf("=")+1);
        String sts4=temp[4].substring(temp[4].lastIndexOf("=")+1).replace("}","");
        Fishorders fishorders=new Fishorders();
        fishorders.setChannel("信息流_百度");
        fishorders.setPhone(sts3);
        fishorders.setName(sts2);
        fishorders.setInterior(sts4);
        fishorders.setNameofadvertiser("杭州睿通基木鱼");
        fishorders.setServicename("");
        fishorders.setStatos("待外呼");
        fishorders.setOrdertime(sfs.format(date));
        int results=flyIngService.insertOne(fishorders);
        if (results>0){
            return  Result.success(1,"成功");
        }else{
            return  Result.fail(0,"失败");
        }
    }

    @RequestMapping(value = "/hzbuildingblocksoffish",method = RequestMethod.POST)
    @ResponseBody
    public Result baidujmy(@RequestBody Map map){
        Map maps=new HashMap();
        maps.put("data",map);
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pst=map.toString();
        String pst1=pst.substring(0,pst.length());
        String [] temp=pst1.split(", ");
        String sts=temp[0].substring(temp[0].lastIndexOf("=")+1);
        String sts1=temp[1].substring(temp[1].lastIndexOf("=")+1);
        String sts2=temp[2].substring(temp[2].lastIndexOf("=")+1);
        String sts3=temp[3].substring(temp[3].lastIndexOf("=")+1);
        String sts4=temp[4].substring(temp[4].lastIndexOf("=")+1).replace("}","");
        Fishorders fishorders=new Fishorders();
        fishorders.setChannel("信息流_百度");
        fishorders.setPhone(sts3);
        fishorders.setName(sts2);
        fishorders.setInterior(sts4);
        fishorders.setNameofadvertiser("杭州合讯基木鱼");
        fishorders.setServicename("");
        fishorders.setStatos("待外呼");
        fishorders.setOrdertime(sfs.format(date));
        int results=flyIngService.insertOne(fishorders);
        if (results>0){
            return  Result.success(1,"成功");
        }else{
            return  Result.fail(0,"失败");
        }
    }

    @RequestMapping(value = "/nbrtbuildingblocksoffish",method = RequestMethod.POST)
    @ResponseBody
    public Result proactive(@RequestBody Map map){
        Map maps=new HashMap();
        maps.put("data",map);
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pst=map.toString();
        String pst1=pst.substring(0,pst.length());
        String [] temp=pst1.split(", ");
        String sts=temp[0].substring(temp[0].lastIndexOf("=")+1);
        String sts1=temp[1].substring(temp[1].lastIndexOf("=")+1);
        String sts2=temp[2].substring(temp[2].lastIndexOf("=")+1);
        String sts3=temp[3].substring(temp[3].lastIndexOf("=")+1);
        String sts4=temp[4].substring(temp[4].lastIndexOf("=")+1).replace("}","");
        Fishorders fishorders=new Fishorders();
        fishorders.setChannel("信息流_百度");
        fishorders.setPhone(sts3);
        fishorders.setName(sts2);
        fishorders.setInterior(sts4);
        fishorders.setNameofadvertiser("百度宁波睿通");
        fishorders.setServicename("");
        fishorders.setStatos("待外呼");
        fishorders.setOrdertime(sfs.format(date));
        int results=flyIngService.insertOne(fishorders);
        if (results>0){
            return  Result.success(1,"成功");
        }else{
            return  Result.fail(0,"失败");
        }
    }

}

package com.brokerage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brokerage.entity.*;
import com.brokerage.result.Result;
import com.brokerage.service.*;
import com.brokerage.util.ISaleHttpUtil;
import com.brokerage.util.ISaleHttpUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/workinfo")
public class WorkInfoController {

    @Resource
    private WorkInfoService workInfoService;
    @Resource
    private EmployeeService emplService;
    @Resource
    private ProdService proService;
    @Resource
    private HistoryInfoService historyInfoService;
    @Resource
    private AutoAcceptService autoAcceptService;
    @Resource
    private WorkorderlistService workorderlistService;


    @RequestMapping(value = "/autoaccept",method =RequestMethod.POST)
    public Result autoaccept(ISaleHttpUtil iSaleHttpUtil,@RequestParam Map map)throws Throwable{

        return autoAcceptService.autoaccept(iSaleHttpUtil,map);
    }

    @RequestMapping(value = "/autoaccepts",method =RequestMethod.POST)
    public Result autoaccepts(ISaleHttpUtil iSaleHttpUtil,@RequestParam Map map)throws Throwable{

        return autoAcceptService.autoaccepts(iSaleHttpUtil,map);
    }

   @RequestMapping(value = "xiustatues",method = RequestMethod.POST)
   @ResponseBody
    public Result queryAll(@RequestParam Map map){
       Date date=new Date();
       SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       JSONObject data = JSON.parseObject(map.get("data").toString().trim());
       JSONObject datas = data.getJSONObject("data");
       int length = Integer.parseInt(data.get("length").toString());
       int results=0;
       for (int i = 0; i < length; i++) {
           JSONObject datajson = datas.getJSONObject(String.valueOf(i));
           String historys = datajson.getString("historys");
           String oldstatos = datajson.getString("oldstatos");
           String workid = datajson.getString("workid");
           String operator = datajson.getString("operator");
           String status = datajson.getString("status");
           History history = new History();
           String sj = sfs.format(date);
           history.setSevenstatus("");
           history.setWorkid(Integer.parseInt(workid));
           history.setUplogintime(sj);
           history.setStatosname(oldstatos);
           history.setUplognno(operator);
           history.setState(status);
           history.setHistorys(historys);
           int result = historyInfoService.insertOneHistory(history);
           if (result > 0) {
               int hid = history.getId();
               workInfoService.updatevery(status, String.valueOf(hid), workid);
               continue;
           } else {
               return Result.fail(0,"审核失败");
           }
       }
       return Result.success(1,"运行成功");
    }

    @RequestMapping(value = "chaxunre",method = RequestMethod.POST)
    public Result ChaxunRe(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        Employee employee=new Employee();
        Department department=new Department();
        Prod prod=new Prod();
        OrderInfo orderInfo=new OrderInfo();
        Cust cust=new Cust();
        int countRate=0;
        int countintegral=0;
        int countsub=0;
        List<Money> moneyList = new ArrayList<>();
        try{
            List<Work> lists = workInfoService.queryAll(map);
            for (int i=0;i<lists.size();i++) {
                Money money=new Money();
                Work work=lists.get(i);
                prod=work.getPid();
                orderInfo=work.getOid();
                cust=work.getCid();
                money.setCustName(cust.getCustname());
                money.setPhone(cust.getCustphone());
                money.setId(work.getId());
                money.setDealthetime(work.getCompletedtime());
                money.setStatus(work.getStatos());
                money.setStatusType(work.getVerify());
                money.setProdName(prod.getProductsName());
                money.setDepaName(prod.getDepaname());
                money.setServiceName(work.getServiceName());
                money.setUploginName(work.getAssigneeName());
                money.setRate(prod.getRate());
                money.setOrderNo(orderInfo.getOrderNo());
                money.setIntegral(prod.getIntegral());
                money.setSubsidy(prod.getSubsidy());
                money.setDeduct(prod.getDeduct());
                moneyList.add(money);
                countRate=countRate+Integer.parseInt(prod.getRate());
                countintegral=countintegral+Integer.parseInt(prod.getIntegral());
                countsub=countsub+Integer.parseInt(prod.getSubsidy());
            }

        }catch (Exception e){
        }
        PageInfo<Money> pageInfos = new PageInfo(moneyList);
        Map maps=new HashMap();
        maps.put("lists",pageInfos);
        maps.put("countRate",countRate);
        maps.put("countintegral",countintegral);
        maps.put("countsub",countsub);
        return Result.success(1,maps);
    }

    @RequestMapping(value = "chaxun",method = RequestMethod.POST)
    public Result ChaXun(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        String servicename=null;
        String depaName=null;
        String pid=null;
        Employee employee=new Employee();
        Department department=new Department();
        Prod prod=new Prod();
        OrderInfo orderInfo=new OrderInfo();
        Cust cust=new Cust();
        int countMoney=0;
        List<Money> moneyList = new ArrayList<>();
        try {
            List<Work> lists = workInfoService.queryAll(map);
            for (Work work : lists) {
                Money money=new Money();
                servicename = work.getServiceName();
                pid = work.getAcceptid();
                employee = emplService.queryAllDe(servicename);
                department = employee.getDepasid();
                depaName = department.getDname();
                prod = proService.queryAlldepa(Integer.parseInt(pid), depaName);
                orderInfo=work.getOid();
                cust=work.getCid();
                money.setCustName(cust.getCustname());
                money.setPhone(cust.getCustphone());
                money.setId(work.getId());
                money.setDealthetime(work.getCompletedtime());
                money.setStatus(work.getStatos());
                money.setStatusType(work.getSettlementstatus());
                money.setProdName(prod.getProductsName());
                money.setDepaName(prod.getDepaname());
                money.setServiceName(work.getServiceName());
                money.setUploginName(work.getAssigneeName());
                money.setDeduct(prod.getDeduct());
                money.setOrderNo(orderInfo.getOrderNo());
                moneyList.add(money);
                countMoney=countMoney+Integer.parseInt(prod.getDeduct());

            }
        }catch (Exception e){
        }
        PageInfo<Money> pageInfo = new PageInfo(moneyList);
        Map maps=new HashMap();
        maps.put("lists",pageInfo);
        maps.put("countMoney",countMoney);
        return Result.success(1,maps);
    }

    @RequestMapping(value = "/pxiu",method = RequestMethod.POST)
    @ResponseBody
    public Result uploginno(@RequestParam Map map) {
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           JSONObject data = JSON.parseObject(map.get("data").toString().trim());
           JSONObject datas = data.getJSONObject("data");
           int length = Integer.parseInt(data.get("length").toString());
           int results=0;
           for (int i = 0; i < length; i++) {
               JSONObject datajson = datas.getJSONObject(String.valueOf(i));
               String historys = datajson.getString("historys");
               String oldstatos = datajson.getString("oldstatos");
               String workid = datajson.getString("workid");
               String operator = datajson.getString("operator");
               String status = datajson.getString("status");
               History history = new History();
               String sj = sfs.format(date);
               history.setSevenstatus("");
               history.setWorkid(Integer.parseInt(workid));
               history.setUplogintime(sj);
               history.setStatosname(oldstatos);
               history.setUplognno(operator);
               history.setState(status);
               history.setHistorys(historys);
               int result = historyInfoService.insertOneHistory(history);
               if (result > 0) {
                   int hid = history.getId();
                   workInfoService.updatesettle(status, String.valueOf(hid), workid);
                   continue;
               } else {
                   return Result.fail(0,"审核失败");
               }
           }
           return Result.success(1,"运行成功");
    }

    @RequestMapping(value = "/queryAllWorkorder",method = RequestMethod.POST)
    public Result queryAllWorkorder(@RequestParam Map map){
        return workorderlistService.queryAllWorkorder(map);
    }


}

package com.service.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.service.entity.*;
import com.service.enums.ErrorEnum;
import com.service.result.Result;
import com.service.service.*;
import com.service.util.HttpClientUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/cust")
public class CustController {

    /*
     * 对象类声明
     * */
    OrderInfo order = new OrderInfo();//订单基本信息
    MarketerInfo mark = new MarketerInfo();//营销人信息表
    CustomerInfo custo = new CustomerInfo();//客户信息表
    Orderdetails orderdeta = new Orderdetails();//订单详情表
    OrderHstory orderHstory=new OrderHstory();

    //数据反刷接口
    public static String url = "http://192.168.0.253:2017/order/bss7";

    @Resource
    private CustomerService customerService;
    @Resource
    private MarketerService marketerService;
    @Resource
    private OrderdetailService orderdetailService;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private WorkService workService;


    @RequestMapping(value = "/cha", method = RequestMethod.POST)
    public Result creat(@RequestParam Map map) throws Exception {

        Date date=new Date();
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderNo(map.get("orderNo").toString());
        orderInfo.setOstats("待反刷");
        orderInfo.setAcceptancetime(sj);
        orderInfo.setOrderdetailsId("0");
        orderInfo.setMarkId(0);
        orderInfo.setCustId(0);
        orderInfo.setAbnormal(0);
        int Finding=orderInfoService.chaOrderId(map.get("orderNo").toString());
        if (Finding>0){
            return Result.fail(0,ErrorEnum.CHONG_FU);
        }else {
            int result=orderInfoService.insertOneOrderNo(orderInfo);
            if (result>0){
                workService.updateStatue("待反刷",map.get("workid").toString(),String.valueOf(orderInfo.getId()));
                return Result.success(1,"保存成功");
            }else {
                return Result.fail(0,ErrorEnum.FIAL_ERROR);
            }
        }
    }

    @Resource
    private CustHttpService custHttpService;

    @RequestMapping(value = "/sdsd",method = RequestMethod.POST)
    public int stst()throws Exception{
       custHttpService.Timeupda();
        return 0;
    }

    @RequestMapping(value = "/deleteorder",method = RequestMethod.POST)
    public Result deleteOrderId(@RequestParam(defaultValue = "0",value ="workid")String workid,@RequestParam(defaultValue = "0",value ="orderno")String orderno){
        int results=orderInfoService.deleteOrderinfo(orderno);
        if (results>0&&orderno!="0"){
            int result=workService.updateOrderId(0,workid,"已清空","已清空");
           if (result>0){
               return Result.success(1,"清空成功");
           }else {
               return Result.success(0,"7工单清空失败");
           }
        }else {
            return Result.success(0,"工单查询失败");
        }
    }
}

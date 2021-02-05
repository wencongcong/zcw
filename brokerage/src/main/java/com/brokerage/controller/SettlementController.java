package com.brokerage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brokerage.entity.History;
import com.brokerage.result.Result;
import com.brokerage.service.SettlementAnalusisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/sett")
public class SettlementController {

    @Resource
    private SettlementAnalusisService settlementAnalusisService;


    @RequestMapping(value = "/setts",method = RequestMethod.POST)
    public Result queryAllSett(@RequestParam Map map){

        return settlementAnalusisService.queryAll(map);
    }

    @RequestMapping(value = "/settslist",method = RequestMethod.POST)
    public Result queryAllSettlist(@RequestParam Map map){

        return settlementAnalusisService.settlement(map);
    }

    @RequestMapping(value = "/settstauts",method = RequestMethod.POST)
    @ResponseBody
    public Result chenagestatuts(@RequestBody Map map){
        JSONObject data= JSON.parseObject(map.get("data").toString().trim());
        int length=Integer.parseInt(map.get("length").toString());
        String status=map.get("status").toString();
        if (length <=50) {
            for (int i = 0; i < length; i++) {
                String orderno = data.get(i).toString();
//                History history=new History();
//                history.setStatosname(statosname);
//                history.setState(statosname);
//                history.setUplogintime(sfs.format(new Date()));
//                history.setUplognno(uploginname);
//                history.setWorkid(id);
//                history.setHistorys("");
//                history.setCurentname(servicename);
//                history.setIsitright(1);
//                historyInfoService.insertOneHistory(history);
//                int resul = terminalService.chastatos("营销成功", id);
                int result=settlementAnalusisService.updateSettStatus(orderno,status);
                if (result>0){
                   continue;
                }else{
                    return Result.success(0,"批量结算失败");
                }
            }

        } else {
            return Result.success(0, "一次分配最多不能分配超过50条");
        }
        return Result.success(1,"运行结束");
    }

}

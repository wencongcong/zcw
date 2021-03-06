package com.brokerage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brokerage.entity.History;
import com.brokerage.entity.Tenthousand;
import com.brokerage.result.Result;
import com.brokerage.service.AutoAcceptService;
import com.brokerage.service.ConfigurationService;
import com.brokerage.service.HistoryInfoService;
import com.brokerage.service.TenthousandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/tent")
public class TenthousandController {

    @Resource
    private TenthousandService tenthousandService;
    @Resource
    private ConfigurationService configurationService;
    @Resource
    private AutoAcceptService autoAcceptService;
    @Resource
    private HistoryInfoService historyInfoService;

    @RequestMapping(value = "configurationinformation",method = RequestMethod.POST)
    public Result queryinformation(@RequestParam Map map){
        return tenthousandService.queryAllTen(map);
    }
    @RequestMapping(value = "configurationinformations",method = RequestMethod.POST)
    public Result queryinformations(@RequestParam Map map){
        return tenthousandService.queryAllTens(map);
    }

    @RequestMapping(value = "increasetheconfiguration",method = RequestMethod.POST)
    public Result insertinformation(@RequestParam Map map){
        return tenthousandService.insertTenth(map);
    }

    @RequestMapping(value = "modificationtheconfiguration",method = RequestMethod.POST)
    public Result updatetinformation(@RequestParam Map map){
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        History history=new History();
        String sj=sfs.format(date);
        history.setWorkid(Integer.parseInt(map.get("workid").toString()));
        history.setUplogintime(sj);
        history.setUplognno(map.get("uploginno").toString());
        history.setOldname(map.get("oldname").toString());
        history.setCurentname(map.get("curentname").toString());
        history.setStatosname(map.get("oldstatus").toString());
        history.setState(map.get("status").toString());
        history.setSevenstatus("");
        history.setHistorys(map.get("historys").toString());
        history.setIsitright(Integer.parseInt(map.get("isitright").toString()));
        history.setUpdatemotion("??????");
        historyInfoService.insertOneHistory(history);
        return tenthousandService.updateTenth(map);
    }

    @RequestMapping(value = "canceltheconfiguration",method = RequestMethod.POST)
    public Result deletetinformation(@RequestParam(value = "id",defaultValue = "0")int id){
        return tenthousandService.deleteTenth(id);
    }

    @RequestMapping(value = "queryAlltenthousan",method = RequestMethod.POST)
    public Result querytenthousan(@RequestParam Map map){
        return configurationService.queryAllConfig();
    }

    @RequestMapping(value = "inserttenthousan",method = RequestMethod.POST)
    public Result insertenthousan(@RequestParam Map map){
        return configurationService.insertOneConfig(map);
    }

    @RequestMapping(value = "updatetenthousan",method = RequestMethod.POST)
    public Result updatetenthousan(@RequestParam Map map){
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        History history=new History();
        String sj=sfs.format(date);
        history.setWorkid(Integer.parseInt(map.get("workid").toString()));
        history.setUplogintime(sj);
        history.setUplognno(map.get("uploginno").toString());
        history.setOldname(map.get("oldname").toString());
        history.setCurentname(map.get("curentname").toString());
        history.setStatosname(map.get("oldstatus").toString());
        history.setState(map.get("status").toString());
        history.setSevenstatus("");
        history.setHistorys(map.get("historys").toString());
        history.setIsitright(Integer.parseInt(map.get("isitright").toString()));
        history.setUpdatemotion("??????");
        historyInfoService.insertOneHistory(history);
        return configurationService.updateConfig(map);
    }

    @RequestMapping(value = "deletetenthousan",method = RequestMethod.POST)
    public Result deletetenthousan(@RequestParam(value = "id",defaultValue = "0")int id){
        return configurationService.deleteConfig(id);
    }

    @RequestMapping(value = "aboutbusiness",method = RequestMethod.POST)
    public Result aboutbusiness(@RequestParam Map map){
        return autoAcceptService.sctent(map);
    }

    @RequestMapping(value = "/xiuuplogin",method = RequestMethod.POST)
    public Result xiuuplogin(@RequestParam Map map) throws Exception{
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject data= JSON.parseObject(map.get("data").toString().trim());
        JSONObject datas= data.getJSONObject("data");
        int length=Integer.parseInt(data.get("length").toString());
        String servicename=data.get("servicename").toString();
        String his=data.getString("historys");
        if (length <=50) {
            for (int i = 0; i < length; i++) {
                String uplogin = datas.get(i).toString();
                int id = Integer.parseInt(uplogin);
                String statosname=tenthousandService.chaStotus(id);
                String uploginname=tenthousandService.chaServicename(id);
                History history=new History();
                history.setStatosname(statosname);
                history.setState(statosname);
                history.setUplogintime(sfs.format(new Date()));
                history.setUplognno(uploginname);
                history.setWorkid(id);
                history.setHistorys(his);
                history.setCurentname(servicename);
                history.setIsitright(Integer.parseInt(map.get("isitright").toString()));
                history.setUpdatemotion("??????");
                historyInfoService.insertOneHistory(history);
                int resul = tenthousandService.chastatos("????????????", id);
                if (resul == 1) {
                    return Result.fail(0, "????????????????????????,??????????????????:"+id);
                }else{
                    if (uplogin != null || uplogin != "") {
                        int result = tenthousandService.uploginno(servicename, id);
                        if (result > 0) {
                            continue;
                        } else {
                            return Result.success(0, "????????????");
                        }
                    }
                }
            }
        } else {
            return Result.success(0, "????????????????????????????????????50???");
        }
        return Result.success(1, "????????????");
    }
}

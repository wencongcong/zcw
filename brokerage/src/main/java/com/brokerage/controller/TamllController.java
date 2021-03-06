package com.brokerage.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brokerage.entity.History;
import com.brokerage.entity.Money;
import com.brokerage.entity.Terminal;
import com.brokerage.entity.Tmall;
import com.brokerage.enums.ErrorEnum;
import com.brokerage.result.Result;
import com.brokerage.service.AutoAcceptService;
import com.brokerage.service.HistoryInfoService;
import com.brokerage.service.TamllService;
import com.brokerage.service.TerminalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/tamll")
public class TamllController {


    @Resource
    private TamllService tamllService;
    @Resource
    private AutoAcceptService autoAcceptService;
    @Resource
    private TerminalService terminalService;
    @Resource
    private HistoryInfoService historyInfoService;

    @RequestMapping(value = "/querytamll",method = RequestMethod.POST)
    public Result queryAll(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Tmall>list= tamllService.queryAllTmall(map);
        PageInfo<Tmall> pageInfos = new PageInfo(list);
        return Result.success(1,pageInfos);
    }
    @RequestMapping(value = "/querytamlls",method = RequestMethod.POST)
    public Result queryAlls(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Tmall>list= tamllService.queryAllTmalls(map);
        PageInfo<Tmall> pageInfos = new PageInfo(list);
        return Result.success(1,pageInfos);
    }

    @RequestMapping(value = "/inserttam",method = RequestMethod.POST)
    public Result inserttamll(@RequestParam Map map){
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("createtime",sfs.format(new Date()));
        int result=tamllService.insertOneTmall(map);
        if (result>0){
            return Result.success(1,"????????????");
        }else{
            return Result.success(0,"????????????");
        }
    }

    @RequestMapping(value = "/updatetamll",method = RequestMethod.POST)
    public Result updatetamll(@RequestParam Map map){
        int result=tamllService.updateTmall(map);
        if (result>0){
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
            return Result.success(1,"????????????");
        }else{
            return Result.success(0,"????????????");
        }
    }

    @RequestMapping(value = "/tamllzhuan",method = RequestMethod.POST)
    public Result liuz(@RequestParam Map map){
        return autoAcceptService.sctamll(map);
    }

    @RequestMapping(value = "/exceltamll",method = RequestMethod.POST)
    @ResponseBody
    public Result  Exceltamll(@RequestParam("empFile") MultipartFile empFile, HttpServletRequest request) throws Exception{
        MultipartRequest multipartRequest=(MultipartRequest) request;
        ImportParams params=new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setLastOfInvalidRow(0);
        List<Tmall> list=null;
        try {
            list= ExcelImportUtil.importExcel(empFile.getInputStream(), Tmall.class, params);
            for (Tmall fishorders:list){
                int result=tamllService.insertTmallExcel(fishorders);
                if (result==0){
                    return Result.fail(0,"????????????");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.fail(1,"????????????");
    }


    @RequestMapping(value = "/queryterminl",method = RequestMethod.POST)
    public Result queryterminl(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Terminal>list= terminalService.queryAllTerminal(map);
        PageInfo<Terminal> pageInfos = new PageInfo(list);
        return Result.success(1,pageInfos);
    }
    @RequestMapping(value = "/queryterminls",method = RequestMethod.POST)
    public Result queryterminls(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Terminal>list= terminalService.queryAllTerminals(map);
        PageInfo<Terminal> pageInfos = new PageInfo(list);
        return Result.success(1,pageInfos);
    }

    @RequestMapping(value = "/insertonetrm",method = RequestMethod.POST)
    public Result additiontrm(@RequestParam Map map){
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("createtime",sfs.format(new Date()));
        int result=terminalService.insertOneTerminal(map);
        if (result>0){
            return Result.success(1,"????????????");
        }else{
            return Result.success(0,"????????????");
        }
    }

    @RequestMapping(value = "/terminalzhu",method = RequestMethod.POST)
    @ResponseBody
    public Result orderzhuan(@RequestParam Map map){
        return autoAcceptService.sctreminl(map);
    }

    @RequestMapping(value = "/updateterminal",method = RequestMethod.POST)
    public Result updateterminal(@RequestParam Map map){
        int result=terminalService.updateTerminal(map);
        if (result>0){
            return Result.success(1,"????????????");
        }else{
            return Result.success(0,"????????????");
        }
    }

    @RequestMapping(value = "/excelterminal",method = RequestMethod.POST)
    @ResponseBody
    public Result  Excelterminal(@RequestParam("empFile") MultipartFile empFile, HttpServletRequest request) throws Exception{
        MultipartRequest multipartRequest=(MultipartRequest) request;
        ImportParams params=new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setLastOfInvalidRow(0);
        List<Terminal> list=null;
        try {
            list= ExcelImportUtil.importExcel(empFile.getInputStream(), Terminal.class, params);
            for (Terminal fishorders:list){
                int result=terminalService.insertTerminal(fishorders);
                if (result==0){
                    return Result.fail(0,"????????????");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail(1,"????????????");
    }

    @RequestMapping(value = "/xiuuplogin",method = RequestMethod.POST)
    public Result xiuuplogin(@RequestParam Map map) throws Exception{
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject data= JSON.parseObject(map.get("data").toString().trim());
        JSONObject datas= data.getJSONObject("data");
        int length=Integer.parseInt(data.get("length").toString());
        String servicename=data.get("servicename").toString();
        if (length <=50) {
            for (int i = 0; i < length; i++) {
                String uplogin = datas.get(i).toString();
                int id = Integer.parseInt(uplogin);
                String statosname=terminalService.chaStotus(id);
                String uploginname=terminalService.chaServicename(id);
                History history=new History();
                history.setStatosname(statosname);
                history.setState(statosname);
                history.setUplogintime(sfs.format(new Date()));
                history.setUplognno(uploginname);
                history.setWorkid(id);
                history.setHistorys("");
                history.setCurentname(servicename);
                history.setIsitright(3);
                historyInfoService.insertOneHistory(history);
                int resul = terminalService.chastatos("????????????", id);
                if (resul == 1) {
                    return Result.fail(0, "????????????????????????,??????????????????:"+id);
                }else{
                    if (uplogin != null || uplogin != "") {
                        int result = terminalService.uploginno(servicename, id);
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

    @RequestMapping(value = "/xiuuplogintmall",method = RequestMethod.POST)
    public Result xiuuplogintmall(@RequestParam Map map) throws Exception{
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject data= JSON.parseObject(map.get("data").toString().trim());
        JSONObject datas= data.getJSONObject("data");
        int length=Integer.parseInt(data.get("length").toString());
        String servicename=data.get("servicename").toString();
        if (length <=50) {
            for (int i = 0; i < length; i++) {
                String uplogin = datas.get(i).toString();
                int id = Integer.parseInt(uplogin);
                String statosname=tamllService.chaStotus(id);
                String uploginname=tamllService.chaServicename(id);
                History history=new History();
                history.setStatosname(statosname);
                history.setState(statosname);
                history.setUplogintime(sfs.format(new Date()));
                history.setUplognno(uploginname);
                history.setWorkid(id);
                history.setHistorys("");
                history.setCurentname(servicename);
                history.setIsitright(2);
                historyInfoService.insertOneHistory(history);
                int resul = tamllService.chastatos("????????????", id);
                if (resul == 1) {
                    return Result.fail(0, "????????????????????????,??????????????????:"+id);
                }else{
                    if (uplogin != null || uplogin != "") {
                        int result = tamllService.uploginno(servicename, id);
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

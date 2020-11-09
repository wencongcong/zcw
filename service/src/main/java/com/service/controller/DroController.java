package com.service.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.entity.*;
import com.service.enums.ErrorEnum;
import com.service.result.Result;
import com.service.service.CustService;
import com.service.service.FlyIngService;
import com.service.service.ProdService;
import com.service.service.WorkService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/query")
public class DroController {

    Cust cust = new Cust();
    Work work = new Work();
    History history=new History();

    @Resource
    private CustService custService;
    @Resource
    private WorkService workService;
    @Resource
    private ProdService prodService;
    @Resource
    private FlyIngService flyIngService;

    @RequestMapping(value = "/charu", method = RequestMethod.POST)
    @ResponseBody
    public Object Query(@RequestBody Map map) {
        String maps = JSONObject.toJSONString(map);
        JSONObject datas = JSON.parseObject(maps);
        JSONArray data = datas.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject custinfo = data.getJSONObject(i);
            JSONObject custs = custinfo.getJSONObject("custinfo");
            JSONObject wusts = custinfo.getJSONObject("wordinfo");
            if (custService.chaChong(custs.getString("id")) == 0) {
                cust.setCid(custs.getString("id"));
                cust.setCustname(custs.getString("custname") == "" ? null : custs.getString("custname"));
                cust.setCustphone(custs.getString("phone") == "" ? null : custs.getString("phone"));
                cust.setCustidcard(custs.getString("idcard") == "" ? null : custs.getString("idcard"));
                cust.setCustaddress(custs.getString("address") == "" ? null : custs.getString("address"));
                cust.setCustremark(custs.getString("remark") == "" ? null : custs.getString("remark"));
                cust.setCustcreater(custs.getString("loginno") == "" ? null : custs.getString("loginno"));
                cust.setCustcreatertime(custs.getString("optime") == "" ? null : custs.getString("optime"));
                cust.setCustreserved("");
                int result = custService.insertOneCust(cust);
                if (result > 0 && workService.chaChong(wusts.getString("id")) == 0) {
                    work.setWorkid(wusts.getString("id"));
                    work.setCustid(custs.getString("id"));
                    if (wusts.getString("accept").trim() != null || wusts.getString("accept").trim() != "") {
                     //   int acceptid = prodService.chaAccept(wusts.getString("accept").trim());
                      //  work.setAcceptid(String.valueOf(acceptid));
                    } else {
                        work.setAcceptid("0");
                    }
                    work.setRemark(wusts.getString("remark") == "" ? null : wusts.getString("remark"));
                    work.setServiceName(wusts.getString("serviceName") == "" ? null : wusts.getString("serviceName"));
                    work.setUploginName(wusts.getString("loginno") == "" ? null : wusts.getString("loginno"));
                    work.setAssigneeName(wusts.getString("uploginno") == "" ? null : wusts.getString("uploginno"));
                    work.setBroadband(wusts.getString("broadband") == "" ? null : wusts.getString("broadband"));
                    work.setStatos(wusts.getString("statos") == "" ? null : wusts.getString("statos"));
                    work.setStatus(wusts.getString("status") == "" ? null : wusts.getString("status"));
                    history.setId(0);
                    work.setHid(history);
                    work.setHang("");
                    work.setVerify("");
                    work.setCancel("");
                    work.setCompletedtime("");
                    work.setAppointmenttime(wusts.getString("appointmenttime") == "" ? null : wusts.getString("appointmenttime"));
                    work.setFinanceverify("");
                    work.setSoundverify("");
                    work.setTimepayment(wusts.getString("timepayment") == "" ? null : wusts.getString("timepayment"));
                    work.setPaymentamount(wusts.getString("paymentamount") == "" ? null : wusts.getString("paymentamount"));
                    work.setOrderid(wusts.getString("orderno") == "" ? null : wusts.getString("orderno"));
                    work.setWorkserved("");
                    work.setXdtime(wusts.getString("optime") == "" ? null : wusts.getString("optime"));
                    work.setJsonstr(wusts.getString("jsonstr")== "" ? null : wusts.getString("jsonstr"));
                    work.setProdjson(wusts.getString("prodjson")== "" ? null : wusts.getString("prodjson"));
                    work.setChannl(wusts.getString("channl")== "" ? null : wusts.getString("channl"));
                    work.setSettlementstatus("");
                    int rs = workService.insertOneWork(work);
                    if (rs > 0) {
                        Result.success("插入成功");
                    } else {
                        Result.fail(ErrorEnum.FIAL_ERROR);
                    }
                }
            } else if (custService.chaChong(custs.getString("id")) == 1) {
                work.setWorkid(wusts.getString("id"));
                work.setCustid(custs.getString("id"));
                if (wusts.getString("accept").trim() != null || wusts.getString("accept").trim() != "") {
                  //  int acceptid = prodService.chaAccept(wusts.getString("accept").trim());
                  //  work.setAcceptid(String.valueOf(acceptid));
                } else {
                    work.setAcceptid("0");
                }
                work.setRemark(wusts.getString("remark") == "" ? null : wusts.getString("remark"));
                work.setServiceName(wusts.getString("serviceName") == "" ? null : wusts.getString("serviceName"));
                work.setUploginName(wusts.getString("loginno") == "" ? null : wusts.getString("loginno"));
                work.setAssigneeName(wusts.getString("uploginno") == "" ? null : wusts.getString("uploginno"));
                work.setBroadband(wusts.getString("broadband") == "" ? null : wusts.getString("broadband"));
                work.setStatos(wusts.getString("statos") == "" ? null : wusts.getString("statos"));
                work.setStatus(wusts.getString("status") == "" ? null : wusts.getString("status"));
                history.setId(0);
                work.setHid(history);
                work.setHang("");
                work.setVerify("");
                work.setCancel("");
                work.setCompletedtime("");
                work.setAppointmenttime(wusts.getString("appointmenttime") == "" ? null : wusts.getString("appointmenttime"));
                work.setFinanceverify("");
                work.setSoundverify("");
                work.setTimepayment(wusts.getString("timepayment") == "" ? null : wusts.getString("timepayment"));
                work.setPaymentamount(wusts.getString("paymentamount") == "" ? null : wusts.getString("paymentamount"));
                work.setOrderid(wusts.getString("orderno") == "" ? null : wusts.getString("orderno"));
                work.setWorkserved("");
                work.setXdtime(wusts.getString("optime") == "" ? null : wusts.getString("optime"));
                work.setJsonstr(wusts.getString("jsonstr")== "" ? null : wusts.getString("jsonstr"));
                work.setProdjson(wusts.getString("prodjson")== "" ? null : wusts.getString("prodjson"));
                work.setChannl(wusts.getString("channl")== "" ? null : wusts.getString("channl"));
                work.setSettlementstatus("");
                int rs = workService.insertOneWork(work);
                if (rs > 0) {
                    Result.success("插入成功");
                } else {
                    Result.fail(ErrorEnum.FIAL_ERROR);
                }
            }
        }
        return Result.success("程序运行完毕");
    }

    @RequestMapping(value = "/daoru", method = RequestMethod.POST)
    public Result daoru(@RequestParam Map map) {
        return flyIngService.sgcha(map);
    }

    @RequestMapping(value = "/rexcel", method = RequestMethod.POST)
    @ResponseBody
    public Result rexcel(@RequestParam("file")MultipartFile filePaths, HttpSession session) throws Exception{
        Result result=prodService.queryAllinsert(filePaths);
        return result;
    }

    @RequestMapping(value = "/chaproname",method = RequestMethod.POST)
    public Result queryProdName(@RequestParam Map map){
        List<String> lists=prodService.queryProd(map);
        return Result.success(1,lists);
    }

    @RequestMapping(value = "/insertProd",method = RequestMethod.POST)
    public Result insertProd(@RequestParam Map map){
        Prod prod=new Prod();
        prod.setProMoney(map.get("proMoney").toString());
        prod.setProductsName(map.get("productsName").toString());
        prod.setProCount(map.get("proCount").toString());
        prod.setOtime(map.get("otime").toString());
        prod.setPtime(map.get("ptime").toString());
        prod.setRate(map.get("rate").toString());
        prod.setIntegral(map.get("integral").toString());
        prod.setDeduct(map.get("deduct").toString());
        prod.setSlname(map.get("slname").toString());
        int result=prodService.insetOneProd(prod);
        if (result>0){
            return Result.success(1,"插入成功");
        }else{
            return Result.fail(0,ErrorEnum.FIAL_ERROR);
        }
    }

    @RequestMapping(value = "/queryAll",method = RequestMethod.POST)
    public Result queryAll(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        PageHelper.orderBy("otime desc");
        List<Prod> lists=prodService.queryAll(map);
        PageInfo<Prod> pageInfo = new PageInfo(lists);
        return Result.success(1,pageInfo);
    }

    @RequestMapping(value = "/xiugaiprod",method = RequestMethod.POST)
    public Result updateProd(@RequestParam Map map){
        Prod prod=new Prod();
        prod.setId(Integer.parseInt(map.get("id").toString()));
        prod.setProMoney(map.get("promoney").toString());
        prod.setProductsName(map.get("productsname").toString());
        prod.setProCount(map.get("procount").toString());
        prod.setOtime(map.get("otime").toString());
        prod.setPtime(map.get("ptime").toString());
        prod.setRate(map.get("rate").toString());
        prod.setIntegral(map.get("integral").toString());
        prod.setDeduct(map.get("deduct").toString());
        prod.setSlname(map.get("slname").toString());
        prod.setDepaname(map.get("depaname").toString());
        prod.setEnable(Integer.parseInt(map.get("enable").toString()));
        prod.setSubsidy(map.get("subsidy").toString());
        int result=prodService.updateOneProd(prod);
        if (result>0){
            return Result.success(1,"修改成功");
        }else {
            return Result.fail(0,"修改失败");
        }
    }

    @RequestMapping(value = "/deteleProd",method = RequestMethod.POST)
    public Result deteleProd(@RequestParam Map map){
       int result=prodService.delteOneProd(Integer.parseInt(map.get("id").toString()),map.get("productsName").toString().trim());
        if (result>0){
            return Result.success(1,"删除成功");
        }else {
            return Result.fail(0,"删除失败");
        }
    }

}

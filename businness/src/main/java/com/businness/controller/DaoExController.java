package com.businness.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.*;
import com.alibaba.druid.util.StringUtils;
import com.businness.entity.*;
import com.businness.enums.ErrorEnum;
import com.businness.result.Result;
import com.businness.service.*;
import javafx.concurrent.Worker;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/daoexcel")
public class DaoExController {

    @Resource
    private FishordersDaoService fishordersDaoService;
    @Resource
    private ProdDaoService prodDaoService;
    @Resource
    private WorkDaoService workDaoService;
    @Resource
    private CustDaoService custDaoService;
    @Resource
    private OrderInfosService orderInfosService;
    @Resource
    private ZcdistributorExService zcdistributorExService;
    @Resource
    private CommissionExService commissionExService;


    @RequestMapping(value = "/excelorder",method = RequestMethod.POST)
    public Result Excelorder(@RequestParam Map map){

        List<OrderInfoEX> list=orderInfosService.allTheQuery();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("7工单","7工单"),
                OrderInfoEX.class, list);
        if(workbook==null){
            return Result.fail(0, ErrorEnum.NO_MESSAGE);
        }
        FileOutputStream fps=null;
        try {
            ///www/wwwroot/crm/FileText"+I+".xlsx
            fps=new FileOutputStream("/www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx");
            workbook.write(fps);
            fps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("/file/"+map.get("name").toString()+".xlsx");
    }

    @RequestMapping(value = "/excelfish",method = RequestMethod.POST)
    public Result Excel(@RequestParam Map map){

        List<FishordersEX> list=fishordersDaoService.queryAll(map);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("商机单报表","商机单"),
                FishordersEX.class, list);
        if(workbook==null){
            return Result.fail(0, ErrorEnum.NO_MESSAGE);
        }
        FileOutputStream fps=null;
        try {
            ///www/wwwroot/crm/FileText"+I+".xlsx
            fps=new FileOutputStream("/www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx");
            workbook.write(fps);
            fps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("/file/"+map.get("name").toString()+".xlsx");
    }

    @RequestMapping(value = "/excelprod",method = RequestMethod.POST)
    public Result Excelprod(@RequestParam Map map){

        List<ProdEX> list=prodDaoService.queryAll(map);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("产品表","产品"),
                ProdEX.class, list);
        if(workbook==null){
            return Result.fail(0, ErrorEnum.NO_MESSAGE);
        }
        FileOutputStream fps=null;
        try {
            //www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx
            fps=new FileOutputStream("/www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx");
            workbook.write(fps);
            fps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("/file/"+map.get("name").toString()+".xlsx");
    }

    @RequestMapping(value = "/excelwork",method = RequestMethod.POST)
    public Result Excelwork(@RequestParam Map map){

        List<WorkEX> list=workDaoService.queryAll(map);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("工单报表","工单"),
                WorkEX.class, list);
        if(workbook==null){
            return Result.fail(0, ErrorEnum.NO_MESSAGE);
        }
        FileOutputStream fps=null;
        try {
            ///www/wwwroot/crm/FileText"+I+".xlsx
            fps=new FileOutputStream("/www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx");
            workbook.write(fps);
            fps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("/file/"+map.get("name").toString()+".xlsx");
    }

    @RequestMapping(value = "/exceltent",method = RequestMethod.POST)
    public Result Exceltent(@RequestParam Map map){

        List<ZcdistributorEx> list=zcdistributorExService.queryAll(map);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("分销报表","分销"),
                ZcdistributorEx.class, list);
        if(workbook==null){
            return Result.fail(0, ErrorEnum.NO_MESSAGE);
        }
        FileOutputStream fps=null;
        try {
            ///www/wwwroot/crm/FileText"+I+".xlsx
            fps=new FileOutputStream("/www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx");
            workbook.write(fps);
            fps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("/file/"+map.get("name").toString()+".xlsx");
    }

    @RequestMapping(value = "/daoruexcele",method = RequestMethod.POST)
    @ResponseBody
    public Result  daoruexcele(@RequestParam("empFile") MultipartFile empFile, HttpServletRequest request) throws Exception{
        MultipartRequest multipartRequest=(MultipartRequest) request;
        ImportParams  params=new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setLastOfInvalidRow(1);
        List<FishordersEX> list=null;
        try {
            list= ExcelImportUtil.importExcel(empFile.getInputStream(), FishordersEX.class, params);
            for (FishordersEX fishorders:list){
                int result=fishordersDaoService.insertOneEx(fishorders);
                if (result==0){
                    return Result.fail(0,"插入失败");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.fail(1,"运行结束");
    }

    @RequestMapping(value = "/daoruexcelecomm",method = RequestMethod.POST)
    @ResponseBody
    public Result  daoruexcelecomm(@RequestParam("empFile") MultipartFile empFile, HttpServletRequest request) throws Exception{
        MultipartRequest multipartRequest=(MultipartRequest) request;
        ImportParams  params=new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setLastOfInvalidRow(0);
        List<CommissionEx> list=null;
        try {
            list= ExcelImportUtil.importExcel(empFile.getInputStream(), CommissionEx.class, params);
            for (CommissionEx commissionEx:list){
                int result=commissionExService.insertAllComm(commissionEx);
                if (result==0){
                    return Result.fail(0,"插入失败");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.success(1,"运行结束");
    }


    @RequestMapping(value = "/daoruexcel",method = RequestMethod.POST)
    @ResponseBody
    public Result  daoruexcel(@RequestParam("empFile") MultipartFile empFile, HttpServletRequest request) throws Exception {
        Date date = new Date();
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        MultipartRequest multipartRequest = (MultipartRequest) request;
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setLastOfInvalidRow(0);
        List<WorksEx> list = null;
        WorkEX workEX = new WorkEX();
        CustEX custEX = new CustEX();
        OrderInfoEX orderInfo = new OrderInfoEX();
        int custid = 0;
        int workid = 0;
        int pid = 0;
        int bj = 0;
        int oid = 0;
        try {
            list = ExcelImportUtil.importExcel(empFile.getInputStream(), WorksEx.class, params);
            for (WorksEx worksEX : list) {
                if (StringUtils.isEmpty(worksEX.getCustname()) || StringUtils.isEmpty(worksEX.getStatus()) || StringUtils.isEmpty(worksEX.getAccept()) || StringUtils.isEmpty(worksEX.getServiceName())) {
                    return Result.fail(0, "必要数据不能为空");
                } else if (custDaoService.chaChongCount(worksEX.getCustname(),worksEX.getCustphone()) > 0) {
                    custid = custDaoService.chachongname(worksEX.getCustname(),worksEX.getCustphone());
                    pid = prodDaoService.chaAcceptName(worksEX.getAccept(), worksEX.getDepaname());
                    if (pid == 0) {
                        return Result.fail(0, "产品错误");
                    }
                    if (worksEX.getOrderno() != null) {
                        orderInfo.setOrderNo(worksEX.getOrderno());
                        orderInfo.setOstats(worksEX.getStatus());
                        orderInfo.setAcceptancetime(sj);
                        orderInfo.setOrderdetaiId("0");
                        orderInfo.setMarkId(0);
                        orderInfo.setCustId(0);
                        orderInfo.setAbnormal(0);
                        int Finding = orderInfosService.chaOrderId(worksEX.getOrderno());
                        if (Finding > 0) {
                            return Result.fail(0, ErrorEnum.CHONG_FU);
                        } else {
                            int result = orderInfosService.insertOneOrderNo(orderInfo);
                            if (result > 0) {
                                oid = orderInfo.getId();
                                workid = workDaoService.queryId();
                                workEX.setWorkid(String.valueOf(workid + 1));
                                workEX.setCustid(String.valueOf(custid));
                                workEX.setAcceptid(String.valueOf(pid));
                                workEX.setServiceName(worksEX.getServiceName().trim());
                                workEX.setStatus(worksEX.getStatus().trim());
                                workEX.setBroadband(worksEX.getBroadband() == null ? "" : worksEX.getBroadband());
                                workEX.setRemark(worksEX.getRemark());
                                workEX.setPaymentamount(worksEX.getPaymentamount());
                                workEX.setChannl(worksEX.getChannl());
                                workEX.setOrderid(String.valueOf(oid));
                                workEX.setXdtime(worksEX.getXdtime());
                                workEX.setAssigneeName(worksEX.getAssigneeName());
                                int rest = workDaoService.insertOneWork(workEX);
                                if (rest > 0) {
                                    bj++;
                                    continue;
                                } else {
                                    return Result.fail(0, "插入失败,第" + bj + "失败");
                                }
                            } else {
                                return Result.fail(0, "工单号插入失败");
                            }
                        }
                    } else {
                        return Result.fail(0, "工单号不能为空");
                    }
                } else {
                    custid = custDaoService.queryCid();
                    custEX.setCid(String.valueOf(custid + 1));
                    custEX.setCustname(worksEX.getCustname().trim());
                    custEX.setCustphone(worksEX.getCustphone());
                    custEX.setCustidcard(worksEX.getCustidcard());
                    custEX.setCustaddress(worksEX.getCustaddress());
                    custEX.setCustarea(worksEX.getCustarea() == null ? "其他" : worksEX.getCustarea());
                    custEX.setCustcreatertime(sj);
                    int result = custDaoService.insertOne(custEX);
                    if (result > 0) {
                        custid = custEX.getId();
                        pid = prodDaoService.chaAcceptName(worksEX.getAccept(), worksEX.getDepaname());
                        if (pid == 0) {
                            return Result.fail(0, "产品错误");
                        }
                        if (worksEX.getOrderno() != null) {
                            orderInfo.setOrderNo(worksEX.getOrderno());
                            orderInfo.setOstats(worksEX.getStatus());
                            orderInfo.setAcceptancetime(sj);
                            orderInfo.setOrderdetaiId("0");
                            orderInfo.setMarkId(0);
                            orderInfo.setCustId(0);
                            orderInfo.setAbnormal(0);
                            int Finding = orderInfosService.chaOrderId(worksEX.getOrderno());
                            if (Finding > 0) {
                                return Result.fail(0, ErrorEnum.CHONG_FU);
                            } else {
                                int results = orderInfosService.insertOneOrderNo(orderInfo);
                                if (results > 0) {
                                    oid = orderInfo.getId();
                                    workid = workDaoService.queryId();
                                    workEX.setWorkid(String.valueOf(workid + 1));
                                    workEX.setCustid(String.valueOf(custid));
                                    workEX.setAcceptid(String.valueOf(pid));
                                    workEX.setServiceName(worksEX.getServiceName().trim());
                                    workEX.setStatus(worksEX.getStatus().trim());
                                    workEX.setBroadband(worksEX.getBroadband() == null ? "" : worksEX.getBroadband());
                                    workEX.setRemark(worksEX.getRemark());
                                    workEX.setChannl(worksEX.getChannl());
                                    workEX.setOrderid(String.valueOf(oid));
                                    workEX.setPaymentamount(worksEX.getPaymentamount());
                                    workEX.setXdtime(worksEX.getXdtime());
                                    workEX.setAssigneeName(worksEX.getAssigneeName());
                                    int res = workDaoService.insertOneWork(workEX);
                                    if (res > 0) {
                                        bj++;
                                        continue;
                                    } else {
                                        return Result.fail(0, "插入失败,第" + bj + "失败");
                                    }
                                } else {
                                    return Result.fail(0, "工单号插入失败");
                                }
                            }
                        } else {
                            return Result.fail(0, "信息导入失败");
                        }
                    }
                }
                return Result.success(1, "导入成功");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.success(1, "运行结束");
    }
}

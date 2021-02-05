package com.brokerage.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.brokerage.entity.*;
import com.brokerage.enums.ErrorEnum;
import com.brokerage.result.Result;
import com.brokerage.service.EmployeeService;
import com.brokerage.service.HistoryInfoService;
import com.brokerage.service.ProdService;
import com.brokerage.service.WorkInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/money")
public class MoneyController {

    @Resource
    private WorkInfoService workInfoService;
    @Resource
    private EmployeeService emplService;
    @Resource
    private ProdService proService;
    @Resource
    private HistoryInfoService historyInfoService;


    /**
     * 佣金报表导出
     * */
    @RequestMapping(value = "/chaxunrate",method = RequestMethod.POST)
    public Result ChaxunRe(@RequestParam Map map){
        Employee employee=new Employee();
        Department department=new Department();
        Prod prod=new Prod();
        OrderInfo orderInfo=new OrderInfo();
        Cust cust=new Cust();
        int countRate=0;
        List<MoneyEx> moneyList = new ArrayList<>();
        try{
            List<Work> lists = workInfoService.queryAll(map);
            for (Work work : lists) {
                MoneyEx money=new MoneyEx();
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
                countRate=countRate+Integer.parseInt(prod.getRate());
                moneyList.add(money);
            }
        }catch (Exception e){
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("佣金报表","佣金"),
                MoneyEx.class, moneyList);
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
        return Result.success("/www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx");
    }

    /**
     *提出报表导出
     */
    @RequestMapping(value = "/chaxunduct",method = RequestMethod.POST)
    public Result ChaXun(@RequestParam Map map){
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
                countMoney=countMoney+Integer.parseInt(prod.getDeduct());
                moneyList.add(money);
            }
        }catch (Exception e){
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("提成报表","提成"),
                MoneyEx.class, moneyList);
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
        return Result.success("/www/wwwroot/CRM2/sourse/file/"+map.get("name").toString()+".xlsx");
    }
}

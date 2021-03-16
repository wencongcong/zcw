package com.businness.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WorkEX")
@ExcelTarget(value = "WorkEX")
public class WorkEX implements Serializable {



    int id;
    @Excel(name="订单编号",width = 30,orderNum = "1")
    String workid;
    @ExcelEntity(name = "客户列表")
    CustEX cid;
    String custid;
    @ExcelEntity(name = "产品列表")
    ProdEX pid;
    String acceptid;
    @Excel(name="工单备注",width = 30,orderNum = "1")
    String remark;
    @Excel(name="业务人员",width = 30,orderNum = "1")
    String serviceName;
    @Excel(name="工单创建人员",width = 30,orderNum = "1")
    String uploginName;
    @Excel(name="受理人",width = 30,orderNum = "1")
    String assigneeName;
    @Excel(name="宽带账号",width = 30,orderNum = "1")
    String broadband;
    @Excel(name="状态",width = 30,orderNum = "1")
    String status;
    @Excel(name="7工单反刷状态",width = 30,orderNum = "1")
    String statos;
    @ExcelEntity(name="转状态原因")
    HistoryEx historyid;
    @Excel(name="竣工时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String completedtime;
    @Excel(name="预约时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String appointmenttime;
    @Excel(name="转财务原因",width = 30,orderNum = "1")
    String financeverify;
    @Excel(name="天猫单号",width =30 ,orderNum = "1")
    String soundverify;
    @Excel(name="付款时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String timepayment;
    @Excel(name="付款金额",width = 30,orderNum = "1")
    String paymentamount;
    @ExcelEntity(name = "反刷工单列表")
    OrderInfoEX oid;
    String orderid;
    @Excel(name="工作流",width = 30,orderNum = "1")
    String workserved;
    @Excel(name="下单时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String xdtime;
    @Excel(name="是否催单",width = 30,orderNum = "1")
    int reminder;
    @Excel(name="一级渠道",width = 30,orderNum = "1")
    String channels;
    @Excel(name="二级渠道",width = 30,orderNum = "1")
    String channl;
    @Excel(name = "结算状态",width = 30,orderNum = "1")
    String settlementstatus;
    @Excel(name="收费方式",width = 30,orderNum = "1")
    String paymentstate;
    @Excel(name="客户备注",width = 30,orderNum = "1")
    String jsonstr;
    @Excel(name="终端型号",width = 30,orderNum = "1")
    String terminaltype;
    @Excel(name="终端串号",width = 30,orderNum = "1")
    String terminalseries;
}

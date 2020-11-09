package com.brokerage.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
@Table(name = "MoneyEx")
@ExcelTarget(value = "MoneyEx")
public class MoneyEx implements Serializable {
    @Excel(name="id")
    int id;
    @Excel(name="客户姓名",width = 30,orderNum = "1")
    String custName;
    @Excel(name="联系方式",width = 30,orderNum = "1")
    String phone;
    @Excel(name="竣工时间",width = 30,orderNum = "1")
    String dealthetime;
    @Excel(name="订单状态",width = 30,orderNum = "1")
    String status;
    @Excel(name="部门",width = 30,orderNum = "1")
    String depaName;
    @Excel(name="产品",width = 30,orderNum = "1")
    String prodName;
    @Excel(name="结算状态",width = 30,orderNum = "1")
    String statusType;
    @Excel(name="业务人员",width = 30,orderNum = "1")
    String serviceName;
    @Excel(name="受理人员",width = 30,orderNum = "1")
    String uploginName;
    @Excel(name="提成",width = 30,orderNum = "1")
    String deduct;
    @Excel(name="佣金",width = 30,orderNum = "1")
    String rate;
    @Excel(name="积分",width = 30,orderNum = "1")
    String integral;
    @Excel(name="补贴",width = 30,orderNum = "1")
    String subsidy;
    @Excel(name="7工单单号",width = 30,orderNum = "1")
    String orderNo;
}

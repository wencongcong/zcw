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
@Table(name = "WorksEx")
@ExcelTarget(value = "WorksEx")
public class WorksEx implements Serializable {
    @Excel(name = "id")
    int id;
    @Excel(name="客户姓名(不能为空)",width = 30,orderNum = "1")
    String custname;
    @Excel(name="手机号",width = 30,orderNum = "1")
    String custphone;
    @Excel(name="身份证",width = 30,orderNum = "1")
    String custidcard;
    @Excel(name="地址",width = 30,orderNum = "1")
    String custaddress;
    @Excel(name="地市(浙江省-杭州)",width = 30,orderNum = "1")
    String custarea;
    @Excel(name="产品(不能为空)",width = 30,orderNum = "1")
    String accept;
    @Excel(name="业务人员(不能为空)",width = 30,orderNum = "1")
    String serviceName;
    @Excel(name="工单备注",width = 30,orderNum = "1")
    String remark;
    @Excel(name="状态(不能为空)",width = 30,orderNum = "1")
    String status;
    @Excel(name="宽带账号",width = 30,orderNum = "1")
    String broadband;
    @Excel(name="预约时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String appointmenttime;
    @Excel(name="付款金额",width = 30,orderNum = "1")
    String paymentamount;
    @Excel(name="部门(不能为空)",width = 30,orderNum = "1")
    String depaname;
}

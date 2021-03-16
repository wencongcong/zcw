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
    int id;
    @Excel(name="一级渠道",width = 30,orderNum = "1")
    String channels;
    @Excel(name="二级渠道",width = 30,orderNum = "1")
    String channl;
    @Excel(name="客户姓名(不能为空)",width = 30,orderNum = "1")
    String custname;
    @Excel(name="手机号",width = 30,orderNum = "1")
    String custphone;
    @Excel(name="身份证",width = 30,orderNum = "1")
    String custidcard;
    @Excel(name="地址",width = 30,orderNum = "1")
    String custaddress;
    @Excel(name="省份(浙江省)",width = 30,orderNum = "1")
    String custareas;
    @Excel(name="市级(杭州市)",width = 30,orderNum = "1")
    String custarea;
    @Excel(name="产品(不能为空)",width = 30,orderNum = "1")
    String accept;
    @Excel(name="业务人员(不能为空)",width = 30,orderNum = "1")
    String serviceName;
    @Excel(name="工单备注",width = 30,orderNum = "1")
    String remark;
    @Excel(name="工单号",width = 30,orderNum = "1")
    String orderno;
    @Excel(name="状态(不能为空)",width = 30,orderNum = "1")
    String status;
    @Excel(name="业务号码",width = 30,orderNum = "1")
    String broadband;
    @Excel(name="下单时间(不能为空)如2021-11-12 11:11:11",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String xdtime;
    @Excel(name="付款金额",width = 30,orderNum = "1")
    String paymentamount;
    @Excel(name="部门(不能为空)",width = 30,orderNum = "1")
    String depaname;
    @Excel(name="受理员",width = 30,orderNum = "1")
    String assigneeName;
}

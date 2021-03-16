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
@Table(name = "Tmall")
@ExcelTarget(value = "Tmall")
public class Tmall implements Serializable {

    int id;
    @Excel(name="姓名",width = 30,orderNum = "1")
    String custname;
    @Excel(name="手机号",width = 30,orderNum = "1")
    String phone;
    @Excel(name="身份证号",width = 30,orderNum = "1")
    String idcard;
    @Excel(name="业务号码",width = 30,orderNum = "1")
    String businessnumber;
    @Excel(name="安装地址",width = 30,orderNum = "1")
    String address;
    @Excel(name="天猫单号",width = 30,orderNum = "1")
    String tmallordernumber;
    @Excel(name="渠道",width = 30,orderNum = "1")
    String channl;
    @Excel(name="广告主",width = 30,orderNum = "1")
    String advertiser;
    String remark;
    String createtime;
    String trackingtime;
    String appointmenttime;
    String businessnumbertwo;
    String businessnumbertree;
    String prodctname;
    String prodctnametwo;
    String prodctnametree;
    Work wid;
    String template;
    String paymentterm;
    String paymentmoney;
    String marketingresults;
    @Excel(name="业务人员",width = 30,orderNum = "1")
    String servicename;
    @Excel(name="地市",width = 30,orderNum = "1")
    String area;
    @Excel(name="省份",width = 30,orderNum = "1")
    String areas;
    @Excel(name="一级渠道",width = 30,orderNum = "1")
    String channels;

}

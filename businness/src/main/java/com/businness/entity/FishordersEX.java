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
@Table(name = "FishordersEX")
@ExcelTarget(value = "FishordersEX")
public class FishordersEX implements Serializable {

    int id;
    @Excel(name="线索ID",width = 30,orderNum = "1")
    String workid;
    @Excel(name="渠道",width = 30,orderNum = "1")
    String channel;
    String flow;
    @Excel(name="联系方式",width = 30,orderNum = "1")
    String phone;
    @Excel(name="客户姓名",width = 30,orderNum = "1")
    String name;
    @Excel(name="身份证号",width = 30,orderNum = "1")
    String idcard;
    @Excel(name="安装地址",width = 30,orderNum = "1")
    String interior;
    @Excel(name="办理业务",width = 30,orderNum = "1")
    String accept;
    @Excel(name = "业务员名称",width = 30,orderNum = "1")
    String servicename;
    String uploginno;
    @Excel(name="状态",width = 30,orderNum = "1")
    String statos;
    @Excel(name="数量",width = 30,orderNum = "1")
    String amount;
    @Excel(name="下单时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String ordertime;
    @Excel(name="金额",width = 30,orderNum = "1")
    String payment;
    @Excel(name="备注",width = 30,orderNum = "1")
    String remark;
    @Excel(name="URL",width = 30,orderNum = "1")
    String toproomotelinks;
    @Excel(name="广告主名称",width = 30,orderNum = "1")
    String nameofadvertiser;
    @Excel(name="地市",width = 30,orderNum = "1")
    String address;
    String appointmentime;
    int ok;
    String trackingtime;//跟踪时间
    @Excel(name="失败原因",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String failure;//失败原因
    String uplogintime;//更新时间
    @Excel(name="资产号码",width = 30,orderNum = "1")
    String broadband;
    int isitnew;
    int whethertorepeat;
    WorkEX wid;
    @Excel(name="收款方式",width = 30,orderNum = "1")
    String paymentstate;

}

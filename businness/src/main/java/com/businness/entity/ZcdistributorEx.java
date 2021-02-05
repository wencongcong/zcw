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
@Table(name = "ZcdistributorEx")
@ExcelTarget(value = "ZcdistributorEx")
public class ZcdistributorEx implements Serializable {

    int id;
    String workid;
    @Excel(name="渠道")
    String channel;
    String flow;
    @Excel(name="联系方式")
    String phone;
    @Excel(name="客户姓名")
    String name;
    @Excel(name="身份证号")
    String idcard;
    @Excel(name="安装地址")
    String interior;
    @Excel(name="产品名称")
    String product;
    @Excel(name="业务人员")
    String servicename;
    String uploginno;
    @Excel(name="状态")
    String statos;
    String amount;
    @Excel(name="下单时间")
    String ordertime;
    @Excel(name="金额")
    String payment;
    @Excel(name="备注")
    String remark;
    @Excel(name="链接")
    String toproomotelinks;
    String nameofadvertiser;
    @Excel(name="地市")
    String address;
    int ok;
    String uplogintime;//更新时间
    @Excel(name="id")
    String broadband;
    int isitnew;
    int whethertorepeat;
    @ExcelEntity(name = "工单")
    WorkEX wid;
    @Excel(name="商品名称")
    String commodity;
    @Excel(name="收费方式")
    String paymentmethod;
}

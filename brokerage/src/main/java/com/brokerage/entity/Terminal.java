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
@Table(name = "Terminal")
@ExcelTarget(value = "Terminal")
public class Terminal implements Serializable {

    int id;
    @Excel(name="姓名",width = 30,orderNum = "1")
    String custname;
    @Excel(name="联系电话",width = 30,orderNum = "1")
    String phone;
    String idcard;
    String servicename;
    @Excel(name="业务号码",width = 30,orderNum = "1")
    String businessnumber;
    @Excel(name="地势",width = 30,orderNum = "1")
    String area;
    String marketingresults;
    String channl;
    String advertiser;
    String ordertime;
    String productname;
    String trackingtime;
    String terminalseries;
    String businessnumbertwo;
    String businessnumbertree;
    String terminaltyp;
    String template;
    String combo;
    @Excel(name="备注",width = 30,orderNum = "1")
    String remark;
    Work wid;
    @Excel(name="创建时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
    String createtime;
    String combustible;
    @Excel(name="C4",width = 30,orderNum = "1")
    String cname;
    String address;
    @Excel(name="套餐类型",width = 30,orderNum = "1")
    String nowPackageType;
    @Excel(name="现有优惠",width = 30,orderNum = "1")
    String nowDiscount;
    String packageChange;
    String paymentterm;
    String receivable;
    String productnametwo;
    String productnametree;
    String failure;
}

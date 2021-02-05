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
/*
*
* 订单基本信息表
* */


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderInfoEX")
@ExcelTarget(value = "OrderInfoEX")
public class OrderInfoEX implements Serializable {
    int id;
    @Excel(name="7工单单号",width = 30,orderNum = "1")
    String orderNo;
    @Excel(name="7工单状态",width = 30,orderNum = "1")
    String ostats;
    @Excel(name="固网单单号",width = 30,orderNum = "1")
    String guwangno;
    @Excel(name="固网状态",width = 30,orderNum = "1")
    String guwangstatos;
    @Excel(name="C网单单号",width = 30,orderNum = "1")
    String cwangno;
    @Excel(name="C网状态",width = 30,orderNum = "1")
    String cwangstatos;
    @Excel(name="原单单号",width = 30,orderNum = "1")
    String yuandanno;
    @Excel(name="原单状态",width = 30,orderNum = "1")
    String yuandanstatos;
    @Excel(name="施工电话",width = 30,orderNum = "1")
    String fphone;
    @ExcelEntity(name="行项目信息")
    OrderDetilsEx orderdetailsId;
    String orderdetaiId;
    int markId;
    int custId;
    int abnormal;
    String acceptancetime;
}

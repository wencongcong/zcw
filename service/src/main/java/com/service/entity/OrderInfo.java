package com.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
/*
*
* 订单基本信息表
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderInfo")
public class OrderInfo implements Serializable {
    int id;
    String orderNo;
    String ostats;
    String subtype;
    String batchmark;
    String qiremarks;
    String acceptancetime;
    String ordertype;
    String groupprdernumber;
    String remarks;
    String ordersource;
    String acceptchannal;
    String automatilmarking;
    String chargemethod;
    String acceptancetype;
    String orderdetailsId;
    int markId;
    int custId;
    String guwangno;
    String guwangstatos;
    String cwangno;
    String cwangstatos;
    String yuandanno;
    String yuandanstatos;
    String fphone;
    String rowstate;
    int abnormal;
    String createNo;
    String createName;
    String createPhone;
    String thefirstNo;
    String thefirstPhone;
    String thefirstName;
}

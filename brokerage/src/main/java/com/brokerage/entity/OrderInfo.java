package com.brokerage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/*
*
* 订单基本信息表
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    int orderdetailsId;
    int markId;
    int custId;
    String guwangno;
    String guwangstatos;
    String cwangno;
    String cwangstatos;
    String yuandanno;
    String yuandanstatos;
    String fphone;
}

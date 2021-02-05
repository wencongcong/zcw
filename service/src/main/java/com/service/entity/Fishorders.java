package com.service.entity;

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
@Table(name = "Fishorders")
public class Fishorders implements Serializable {

    int id;
    String workid;
    String channel;
    String flow;
    String phone;
    String name;
    String idcard;
    String interior;
    String accept;
    String servicename;
    String uploginno;
    String statos;
    String amount;
    String ordertime;
    String payment;
    String remark;
    String toproomotelinks;
    String nameofadvertiser;
    String address;
    String appointmentime;
    int ok;
    String trackingtime;//跟踪时间
    String failure;//失败原因
    String uplogintime;//更新时间
    String broadband;
    int isitnew;
    int whethertorepeat;
    Work wid;
    String paymentstate;
}

package com.brokerage.entity;

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
@Table(name = "Work")
public class Work implements Serializable {
    int id;
    String workid;
    Cust cid;
    String custid;
    Prod pid;
    String acceptid;
    String remark;
    String serviceName;
    String uploginName;
    String assigneeName;
    String broadband;
    String status;
    String statos;
    History hid;
    String historyid;
    String hang;
    String verify;
    String cancel;
    String completedtime;
    String appointmenttime;
    String financeverify;
    String soundverify;
    String timepayment;
    String paymentamount;
    String orderid;
    OrderInfo oid;
    String workserved;
    String xdtime;
    String jsonstr;
    String prodjson;
    int reminder;
    String channl;
    String settlementstatus;
    String paymentstate;
    String businessnumber;
    String region;
    String existingpreferential;
    String existingPackageTypes;
    String terminaltype;
    String changedPackagetype;
    String vicecardnumber;
    String custaddress;
    String prodctsName;
    String prodacceptthemethod;
    String terminalseries;

}

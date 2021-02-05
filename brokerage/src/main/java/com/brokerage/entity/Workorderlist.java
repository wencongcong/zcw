package com.brokerage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Workorderlist")
public class Workorderlist implements Serializable {

    int id;
    String createtime;
    String endtime;
    String prodctsName;
    String channl;
    String orderon;
    String custname;
    String orderstatus;
    String chargemanner;
    String acceptchannel;
    String firstdeveloppeople;
    String firstdeveloppeopleno;
    String theassetnumber;
    String behavior;
    String currentstatus;
    String acceptid;
    String orderdetailsid;
    List<OrderdetailsInfo> orderdetailsInfoid;
    String markid;
    String remark;
    String automatilmarking;
    String paymoney;
}

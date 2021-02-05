package com.brokerage.entity;


import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Zcdistributor")
@ExcelTarget(value = "Zcdistributor")
public class Zcdistributor implements Serializable {

    int id;
    String workid;
    String channel;
    String flow;
    String phone;
    String name;
    String idcard;
    String interior;
    String product;
    String servicename;
    String uploginno;
    String statos;
    String amount;
    String ordertime;
    String payment;
    String remark;
    String toproomotelinks;
    String nameofadvertiser;
    String paymentmethod;
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
    String commodity;
}

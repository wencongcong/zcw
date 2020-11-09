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
@Table(name = "Money")
public class Money implements Serializable {
    int id;
    String custName;
    String phone;
    String dealthetime;
    String status;
    String depaName;
    String prodName;
    String statusType;
    String serviceName;
    String uploginName;
    String deduct;
    String rate;
    String integral;
    String subsidy;
    String orderNo;
}

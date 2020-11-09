package com.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 *订单详情表
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orderdetails")
public class Orderdetails implements Serializable {
    int id;
    String orderInfoid;
    String assetnumber;
    String behavior;
    String currentstate;
    String currentpeincharge;
    String operation;
    String processinglink;
    String workorderstatus;
    String dealingpeopre;
    String processingtime;
    String pageNew;
    String prodName;
    String orderjson;

}

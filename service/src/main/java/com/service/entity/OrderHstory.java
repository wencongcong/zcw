package com.service.entity;

import com.alibaba.fastjson.JSONObject;
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
@Table(name = "OrderHstory")
public class OrderHstory implements Serializable {
    int id;
    String OrderID;
    String saleorder;
    String orderDetail;
    String processStatus;
    String baseOrderMessage;
    String uplogintime;
    int biaoji;
}

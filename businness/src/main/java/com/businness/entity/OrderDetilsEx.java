package com.businness.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
@Table(name = "OrderDetilsEx")
@ExcelTarget(value = "OrderDetilsEx")
public class OrderDetilsEx implements Serializable {

    int id;
    @Excel(name="索引ID",width = 30,orderNum = "1")
    String orderinfoid;
    @Excel(name="资产ID",width = 30,orderNum = "1")
    String assetnumber;
    @Excel(name="行为",width = 30,orderNum = "1")
    String behavior;
    @Excel(name="状态",width = 30,orderNum = "1")
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

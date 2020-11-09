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
@Table(name = "ProdEX")
@ExcelTarget(value = "ProdEX")
public class ProdEX implements Serializable {
    int id;
    @Excel(name="金额",width = 30,orderNum = "1")
    String proMoney;
    @Excel(name="产品名称",width = 30,orderNum = "1")
    String productsName;
    @Excel(name="数量",width = 30,orderNum = "1")
    String proCount;
    @Excel(name="生效时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String otime;
    @Excel(name="失效时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String ptime;
    @Excel(name="佣金",width = 30,orderNum = "1")
    String rate;
    @Excel(name="积分",width = 30,orderNum = "1")
    String integral;
    @Excel(name="提成",width = 30,orderNum = "1")
    String deduct;
    @Excel(name="操作说明",width = 30,orderNum = "1")
    String slname;
}

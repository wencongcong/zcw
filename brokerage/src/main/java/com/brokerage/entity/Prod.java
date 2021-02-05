package com.brokerage.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
@Table(name = "Prod")
public class Prod implements Serializable {
    int id;
    @Excel(name="金额")
    String proMoney;
    @Excel(name="产品名称")
    String productsName;
    @Excel(name="数量")
    String proCount;
    @Excel(name="时间")
    String otime;
    @Excel(name="时间")
    String ptime;
    @Excel(name="佣金")
    String rate;
    @Excel(name="积分")
    String integral;
    @Excel(name="提成")
    String deduct;
    @Excel(name="操作说明")
    String slname;
    @Excel(name="部门")
    String depaname;
    @Excel(name="是否启用")
    int enable;
    @Excel(name = "补贴")
    String subsidy;
    @Excel(name="倍率")
    String settlementratio;
}

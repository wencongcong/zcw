package com.brokerage.entity;


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
@Table(name = "Commission")
@ExcelTarget(value = "Commission")
public class Commission implements Serializable {

    int id;
    @Excel(name = "区域")
    String area;
    @Excel(name = "账期")
    String payment;
    @Excel(name = "所属账期")
    String subordinate;
    @Excel(name = "号码")
    String numberphone;
    @Excel(name = "产品类型")
    String producttype;
    @Excel(name = "方案ID")
    String projectID;
    @Excel(name = "方案名称")
    String projectname;
    @Excel(name = "策略ID")
    String trategyID;
    @Excel(name = "策略名称")
    String nameofstrategy;
    @Excel(name = "佣金说明")
    String commissionshows;
    @Excel(name = "酬金类型")
    String remunerationtype;
    @Excel(name = "网点编码")
    String networkcoding;
    @Excel(name = "代理商编码")
    String agentcode;
    @Excel(name = "代理商名称")
    String nameofagent;
    @Excel(name = "佣金")
    String commission;
    @Excel(name = "结算方式")
    String clearingform;
    @Excel(name = "营销人员编码")
    String marketingstaffcode;
    @Excel(name = "调账原因")
    String reconciliationreason;
    @Excel(name="网点名称")
    String networkname;

}

package com.service.domeVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.soap.SAAJResult;
import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FishordVo")
public class FishordVo implements Serializable {

    int id;

    String businessparameters;//商机数
    String workingodd;//工单数
    String calloutinto;//外呼转化率
    String completime;//竣工率
    String acceptnum;//已受理
    String pigeonholenum;//营销成功
    String submitnum;//已提交
    String chargebacknum;//废弃
    String chargeback;//退单率


    String servicename;
    String worknum;//个人总工单
    Integer succdorders;//个人成功
    Integer abas;//个人失败
}

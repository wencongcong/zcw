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
@Table(name = "SettlementAnalusis")
public class SettlementAnalusis implements Serializable {

    int id;
    String assetnumber;
    String months;
    String T1;
    String T2;
    String T3;
    String T4;
    String T5;
    String T6;
    String T7;
    String T8;
    String T9;
    String T10;
    String T11;
    String T12;
    String T213;
    String commission;
    String channl;
    String channels;
    String orderno;
    String productsName;
    String acceptthechannel;
    String developingperson;
    String tollcollectionmanner;
    String prefectural;
    String commissionsubtotal;
    String shouldbecommission;
    String statucts;
    String commissionpart;
    String querylist;
    String producttype;
    String behavior;
    String scenarioname;
    String strategyname;
    String commissiontype;
    String networkname;
    String adjustAccountReason;
}

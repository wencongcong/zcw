package com.service.domeVo;


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
@Table(name = "FishorderratioVo")
public class FishorderratioVo implements Serializable {


    String businessparameterslv;//商机数
    String workingoddlv;//工单数
    String acceptnumlv;//已受理
    String pigeonholenumlv;//营销成功
    String submitnumlv;//已受理
    String chargebacknumlv;//废弃

    String acceptcount;
    String accept;
    String acceptzb;
}

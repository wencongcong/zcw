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
@Table(name = "HistoryEx")
@ExcelTarget(value = "HistoryEx")
public class HistoryEx implements Serializable {

    int id;
    @Excel(name="旧状态",width = 30,orderNum = "1")
    String statosname;
    @Excel(name="新状态",width = 30,orderNum = "1")
    String state;
    @Excel(name="修改时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String uplogintime;
    @Excel(name="修改员工",width = 30,orderNum = "1")
    String uplognno;
    @Excel(name="工单ID",width = 30,orderNum = "1")
    int workid;
    @Excel(name="原因",width = 30,orderNum = "1")
    String historys;
    @Excel(name="当前处理人",width = 30,orderNum = "1")
    String curentname;
}

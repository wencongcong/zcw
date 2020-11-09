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
@Table(name = "CustEX")
@ExcelTarget(value = "CustEX")
public class CustEX implements Serializable {

    int id;
    @Excel(name="客户id",width = 30,orderNum = "1")
    String cid;
    @Excel(name="客户姓名",width = 30,orderNum = "1")
    String custname;
    @Excel(name="手机号",width = 30,orderNum = "1")
    String custphone;
    @Excel(name="身份证",width = 30,orderNum = "1")
    String custidcard;
    @Excel(name="地址",width = 30,orderNum = "1")
    String custaddress;
    @Excel(name="地势",width = 30,orderNum = "1")
    String custarea;
    @Excel(name="客户备注",width = 30,orderNum = "1")
    String custremark;
    @Excel(name="创建人",width = 30,orderNum = "1")
    String custcreater;
    @Excel(name="创建时间",width = 30,orderNum = "1",databaseFormat = "yyyyMMddHHmmss", format = "")
    String custcreatertime;
}

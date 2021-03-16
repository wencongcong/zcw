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
@Table(name = "Tenthousand")
@ExcelTarget(value = "Tenthousand")
public class Tenthousand implements Serializable {

    int id;
    @Excel(name="姓名",width = 30,orderNum = "1")
    String businessname;
    @Excel(name="身份证号码",width = 30,orderNum = "1")
    String businessidcard;
    @Excel(name="联系方式",width = 30,orderNum = "1")
    String businessphone;
    @Excel(name="业务号码",width = 30,orderNum = "1")
    String businessnumberone;
    @Excel(name="市级",width = 30,orderNum = "1")
    String businessarea;
    @Excel(name="省份",width = 30,orderNum = "1")
    String areas;
    @Excel(name="业务人员",width = 30,orderNum = "1")
    String servicename;
    @Excel(name="产品名称",width = 30,orderNum = "1")
    String prodctone;
    @Excel(name="创建时间",width = 30,orderNum = "1")
    String createtime;
    @Excel(name="跟踪时间",width = 30,orderNum = "1")
    String trackingtime;
    @Excel(name="广告主",width = 30,orderNum = "1")
    String advertiser;
    @Excel(name="来源",width = 30,orderNum = "1")
    String source;
    @Excel(name="营销结果",width = 30,orderNum = "1")
    String marketingresults;
    @Excel(name="业务号码2",width = 30,orderNum = "1")
    String businessnumbertwo;
    @Excel(name="业务号码3",width = 30,orderNum = "1")
    String businessnumbertree;
    @Excel(name="产品名称2",width = 30,orderNum = "1")
    String prodcttwo;
    @Excel(name="产品名称3",width = 30,orderNum = "1")
    String prodcttree;
    String template;
    @Excel(name="应收金额",width = 30,orderNum = "1")
    String receivable;
    @Excel(name="收款方式",width = 30,orderNum = "1")
    String payment;
    @Excel(name="备注",width = 30,orderNum = "1")
    String remark;
    @Excel(name="线索ID",width = 30,orderNum = "1")
    String marketing;
    @Excel(name="宽带账号",width = 30,orderNum = "1")
    String broadband;
    @Excel(name="线索ID",width = 30,orderNum = "1")
    String secondaryCard;
    Work wid;
    @Excel(name="安装地址",width = 30,orderNum = "1")
    String address;
    @Excel(name="二级渠道",width = 30,orderNum = "1")
    String channl;
    @Excel(name="一级渠道",width = 30,orderNum = "1")
    String channels;
}

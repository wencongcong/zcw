package com.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户信息表
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo implements Serializable {

    int id;
    String custmername;
    String custmeraddress;
    String custmerNo;
    String docuNumber;
    String custtype;
    String custlevel;
    String contractnumber;
    String accountname;
}

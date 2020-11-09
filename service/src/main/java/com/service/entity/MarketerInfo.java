package com.service.entity;
/*
* 营销人信息表
*
* */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketerInfo implements Serializable {

    int id;
    String createNo;
    String thefirstNo;
    String createName;
    String thefirstPhone;
    String createPhone;
    String thefirstName;

}

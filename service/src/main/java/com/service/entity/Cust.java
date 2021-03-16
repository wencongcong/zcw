package com.service.entity;

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
@Table(name = "Cust")
public class Cust implements Serializable {
    int id;
    String cid;
    String custname;
    String custphone;
    String custidcard;
    String custaddress;
    String custareas;
    String custarea;
    String custremark;
    String custcreater;
    String custcreatertime;
    String custreserved;
}

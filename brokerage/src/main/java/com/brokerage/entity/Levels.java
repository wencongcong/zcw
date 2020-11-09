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
@Table(name = "Levels")
public class Levels implements Serializable {
    int id;
    String lname;
    String laccept;
    String ltime;
    int ljiami;
    int lxiazai;
    int lshangchuang;
    String djson;
    String darr;
    int lfenpei;
}

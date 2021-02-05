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
@Table(name = "History")
public class History implements Serializable {

    int id;
    String statosname;
    String state;
    String uplogintime;
    String uplognno;
    int workid;
    String sevenstatus;
    String historys;
    String curentname;
    int isitright;
}

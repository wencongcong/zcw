package com.brokerage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/*
 *部门
 * */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Department")
public class Department implements Serializable {
    int id;
    String dname;
    String daccept;
    String dtime;

}

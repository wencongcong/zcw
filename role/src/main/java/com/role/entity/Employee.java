package com.role.entity;


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
@Table(name = "Employee")
public class Employee implements Serializable {
    int id;
    String ename;
    String epwd;
    String ephone;
    Levels levelsid;
    int lid;
    Department depasid;
    int did;
}

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
@Table(name = "Inform")
public class Inform implements Serializable {
    int id;
    String name;
    int isoffor;
    String ctime;
    int disno;
}

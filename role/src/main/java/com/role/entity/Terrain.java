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
@Table(name = "Terrain")
public class Terrain implements Serializable {
    int id;
    String areaname;
    String createtime;
    int isopen;
}

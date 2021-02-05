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
@Table(name = "Distributor")
public class Distributor implements Serializable {

    int id;
    String terrain;
    String productname;
    String price;
    String adjustment;
    String productsname;
    String createtime;
    String topimg;
    int bottomid;
    Graphtemplate botid;

}

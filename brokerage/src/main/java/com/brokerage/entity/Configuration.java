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
@Table(name = "Configuration")
public class Configuration implements Serializable {
    int id;
    String area;
    String prodctname;
    String servicename;
    String areas;
}

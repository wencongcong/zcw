package com.businness.entity;

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
@Table(name = "Commodity")
public class Commodity implements Serializable {

    int id;
    String commodityt;
    int wid;
    String uploadtime;
    String uploadname;
}

package com.brokerage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Graphtemplate")
public class Graphtemplate implements Serializable {

    int id;
    String topimg;
    String bottomimg;
    String picturename;
}

package com.role.entity;

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
@Table(name = "Zcprefectural")
public class Zcprefectural implements Serializable {

    int id;
    String areaname;
    int pid;
    List<Zcprefectural> pcid;
}

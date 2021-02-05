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
@Table(name = "Bssverify")
public class Bssverify implements Serializable {

    int id;
    String tel;
    String password;
    String logintime;
    String token;
    String u_info;
    String ordertoken;
    String success;
}

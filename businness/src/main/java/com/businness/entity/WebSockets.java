package com.businness.entity;

import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
@Table(name = "WebSockets")
@ExcelTarget(value = "WebSockets")
public class WebSockets implements Serializable {

    int id;
    String user;
    String fileurl;
    String direction;
    String accept;
    String idname;
    String idnumber;
    String destinationnumber;
    String createdtime;
    String answeredtime;
    String overtime;
    String status;
    String gateway;
    String filename;
    String batchaccept;
    String transnumber;
    String otheraccept;
    String otherstr;
    String olaqueue;
    String hangupside;
}

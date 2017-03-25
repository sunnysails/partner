package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UserLog implements Serializable {
    private Integer id;
    private Timestamp loginTime;
    private String loginIp;
    private Integer userId;

    public UserLog(String loginIp, Integer userId) {
        this.loginIp = loginIp;
        this.userId = userId;
    }
}
package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class User implements Serializable {
    //默认重置密码
    public static final String PASSWORD0 = "000000";
    public static final Integer ENABLE_1 = 1;
    public static final Integer ENABLE_0 = 0;

    private Integer id;
    private String userName;
    private String password;
    private String realName;
    private String weiXin;
    private Timestamp createtime;
    private Boolean enable;
    private Integer roleId;

    public User() {
    }

    public User(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.id = user.getId();
    }
}
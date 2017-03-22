package com.kaishengit.pojo;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString(exclude = "user")
@Entity
@Table(name = "t_user_log")
public class UserLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login_time", insertable = false, updatable = false)
    private Timestamp loginTime;
    @Column(name = "login_ip")
    private String loginIp;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserLog() {
    }

    public UserLog(String loginIp, Integer userId) {
        User user = new User();
        user.setId(userId);
        this.loginIp = loginIp;
        this.user = user;
    }
}
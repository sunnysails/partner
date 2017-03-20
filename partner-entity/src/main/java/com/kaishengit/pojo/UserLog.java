package com.kaishengit.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@ToString(exclude = "user")
@Entity
@Table(name = "t_user_log")
public class UserLog {
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
}
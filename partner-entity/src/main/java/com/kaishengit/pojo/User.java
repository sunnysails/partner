package com.kaishengit.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@ToString(exclude = "role")
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Column(name = "real_name")
    private String realName;
    private String weiXin;
    private Timestamp createtime;
    private Integer enable;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
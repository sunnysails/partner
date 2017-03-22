package com.kaishengit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Setter//lombok注解生成Get/Set 方法
@Getter
//@ToString(exclude = "role")//lombok注解，生成ToString（排除相关列方法）,
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    //默认重置密码
    public static final String PASSWORD0 = "000000";
    public static final Integer ENABLE_1 = 1;
    public static final Integer ENABLE_0 = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Column(name = "real_name")
    private String realName;
    private String weiXin;
    @Column(insertable = false, updatable = false)//排除该列的Insert方法，使数据库默认值生效
    private Timestamp createtime;
    @Column(insertable = false)
    private Integer enable;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;//(roleId = role.id)
    @OneToMany(mappedBy = "user")
    @OrderBy("id desc")
    @JsonIgnore
    private Set<UserLog> userLogList;
}
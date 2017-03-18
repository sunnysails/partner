package com.kaishengit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sunny on 2017/3/15.
 */
@Setter
@Getter
@ToString(exclude = "userList")
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(mappedBy = "role")
    @OrderBy("id desc")
    @JsonIgnore
    private Set<User> userLIst;
}
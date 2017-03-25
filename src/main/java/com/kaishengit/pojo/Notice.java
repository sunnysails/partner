package com.kaishengit.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/3/22.
 */
@Data
public class Notice {
    private Integer id;
    private Integer userid;
    private String title;
    private String context;
    private Timestamp createtime;
    private String realname;
}

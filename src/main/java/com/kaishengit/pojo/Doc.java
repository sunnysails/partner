package com.kaishengit.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/3/18.
 */
@Data
public class Doc {

    public static final String TYPE_DIR = "dir";
    public static final String TYPE_DOC = "doc";

    private Integer id;
    private String name;
    private String size;
    private Timestamp createtime;
    private String createuser;
    private String type;
    private String filename;
    private String md5;
    private Integer fid;
    private String contexttype;
}

package com.kaishengit.pojo;

import lombok.Data;

@Data
public class Task {
    private Integer id;
    private String title;
    private String start;
    private String end;
    private String color;
    private String reminderTime;
    private Integer custId;
    private Integer salseId;
    private Integer userId;
    private Integer status;
}

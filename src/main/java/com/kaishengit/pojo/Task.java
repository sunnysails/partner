package com.kaishengit.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String start;
    private String end;
    private String color;
    @Column(name = "reminder_time")
    private String reminderTime;
/*    @Column(name = "cust_id")
    private long custId;
    @Column(name = "sales_id")
    private long salesId;*/
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer status;
}

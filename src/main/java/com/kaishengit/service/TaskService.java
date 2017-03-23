package com.kaishengit.service;

import com.kaishengit.pojo.Task;

import java.util.List;

/**
 * Created by sunny on 2017/3/22.
 */
public interface TaskService {
    List<Task> findByStartEndWithUser(String start, String end);
}

package com.kaishengit.service.impl;

import com.kaishengit.mapper.TaskMapper;
import com.kaishengit.pojo.Task;
import com.kaishengit.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunny on 2017/3/22.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> findByStartEndWithUser(String start, String end) {
        Integer id = 1;
        //TODO
        return taskMapper.findStartEndWithUser(start, end, id);
    }
}

package com.kaishengit.service.impl;

import com.kaishengit.dao.son.TaskDao;
import com.kaishengit.pojo.Task;
import com.kaishengit.service.TaskService;
import com.kaishengit.shiro.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunny on 2017/3/22.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Override
    public List<Task> findByStartEndWithUser(String start, String end) {
        return taskDao.findStartEndWithUser(start, end, ShiroUtil.getCurrentUserId());
    }
}

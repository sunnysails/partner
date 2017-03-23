package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.pojo.Task;
import com.kaishengit.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunny on 2017/3/22.
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping
    public String taskList() {
        return "/task/list";
    }

    @GetMapping("/load")
    @ResponseBody
    public AjaxResult taskLoad(HttpServletRequest req) {
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        List<Task> taskList = taskService.findByStartEndWithUser(start,end);
        return new AjaxResult(taskList);
    }
}

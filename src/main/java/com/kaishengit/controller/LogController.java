package com.kaishengit.controller;

import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.UserLog;
import com.kaishengit.service.UserService;
import com.kaishengit.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunny on 2017/3/21.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginLog() {
        return "/setting/loglist";
    }

    @GetMapping("/user/login")
    @ResponseBody
    public DataTablesResult loginLog(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
//        String search = request.getParameter("search[value]");
//        search = CharsetUtil.toUTF8(search);
        //TODO 登录日志搜索
        Long count = userService.countWithUser();
        List<UserLog> logList = userService.findUserLoginLog(Integer.valueOf(start), Integer.valueOf(length));
        return new DataTablesResult(draw, count, count, logList);
    }
}

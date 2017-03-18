package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by sunny on 2017/3/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUser() {
        return "user/list";
    }

    @GetMapping
    public String User() {
        return "user/list";
    }

    /*    @GetMapping("/user/load")
        @ResponseBody
        public AjaxResult loadUser(Model model){
            List<User> userList = userService.findAll();
            model.addAttribute("userLIst",userList);
            return new AjaxResult(model);
        }*/
    @GetMapping("/load")
    @ResponseBody
    public DataTablesResult loadUser(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String, Object> queryParam = Maps.newHashMap();
        queryParam.put("start", start);
        queryParam.put("length", length);
        List<User> userList = userService.findAll();

        return new DataTablesResult(draw, 10L, 10L, userList);
    }
}

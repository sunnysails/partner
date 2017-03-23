package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.RoleService;
import com.kaishengit.service.UserService;
import com.kaishengit.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunny on 2017/3/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public String listUser(Model model) {
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "user/list";
    }

    /**
     * 进入用户列表界面
     *
     * @param model
     * @return
     */
    @GetMapping
    public String User(Model model) {
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "user/list";
    }

    /**
     * 读取用户列表
     *
     * @param request
     * @return
     */
    @GetMapping("/load/user")
    @ResponseBody
    public DataTablesResult loadUser(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String search = request.getParameter("search[value]");
        search = CharsetUtil.toUTF8(search);
        Long count = userService.count();
        List<User> userList = userService.findLimitUserOrRealName(Integer.valueOf(start), Integer.valueOf(length), search);

        return new DataTablesResult(draw, count, count, userList);
    }

    /**
     * 判断用户是否存在
     *
     * @param userName
     * @return
     */
    @GetMapping("/isuse")
    @ResponseBody
    public String userNameIsuse(String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return "true";
        }
        return "false";
    }

    /**
     * 添加新用户
     *
     * @param user
     * @return
     */
    @PostMapping("/new")
    @ResponseBody
    public AjaxResult newUser(User user) {
        userService.save(user);
        return new AjaxResult(AjaxResult.SUCCESS);
    }

    /**
     * 重置用户密码为000000
     *
     * @param id
     * @return
     */
    @PostMapping("/resetpassword")
    @ResponseBody
    public AjaxResult resetPassword(Integer id) {
        userService.resetUserPassword(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }

    /**
     * 根据Id返回该相应的user对象
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}.json")
    @ResponseBody
    public AjaxResult showUser(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return new AjaxResult("找不到" + id + "对应的用户");
        } else {
            return new AjaxResult(user);
        }
    }

    /**
     * 编辑用户
     *
     * @param user
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult editUser(User user) {
        userService.updateNoPassword(user);
        return new AjaxResult(AjaxResult.SUCCESS);
    }

}

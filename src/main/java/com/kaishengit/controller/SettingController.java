package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sunny on 2017/3/21.
 */
@Controller
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private UserService userService;

    /**
     * 用户更改密码界面
     *
     * @return
     */
    @GetMapping("/password")
    public String settingPassword() {
        return "/setting/password";
    }

    @PostMapping("/password")
    @ResponseBody
    public AjaxResult settingPassword(String oldPassword, String newPassword) {
        try {
            userService.settingUserPassword(oldPassword, newPassword);
            return new AjaxResult(AjaxResult.SUCCESS);
        } catch (ServiceException e) {
            return new AjaxResult(e.getMessage());
        }
    }
}

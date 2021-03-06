package com.kaishengit.controller;

import com.kaishengit.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunny on 2017/3/15.
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Value("${password.salt}")
    private String salt;
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/")
    public String login(String userName, String password, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        //获取客户端的IP地址
        String ip = req.getRemoteAddr();
        //Shiro 方式登录
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userName, password));
            userService.addLoginLog(ip);
            return "redirect:/home";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");
            return "redirect:/";
        }
    }

    @GetMapping("/home")
    public String home(ServletRequest request) {
        //获取登录前的Url
        try {
            String url = WebUtils.getSavedRequest(request).getRequestUrl();
            if (!url.isEmpty() && !url.equals("/favicon.ico")) {
                return "redirect:" + url;
            } else {
                return "home";
            }
        } catch (NullPointerException e) {
            return "home";
        }
    }

    @RequestMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","你已安全退出");
        return "redirect:/";
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }
}

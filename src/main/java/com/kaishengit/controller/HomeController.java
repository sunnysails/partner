package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunny on 2017/3/15.
 */
@Controller
public class HomeController {
    @RequestMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }
}

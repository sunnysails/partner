package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by sunny on 2017/3/15.
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String login() {
        return "login";
    }
/*
    @PostMapping("/")
    public String login(String userName){
        return null;
    }
*/

}

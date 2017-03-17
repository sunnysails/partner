package com.kaishengit.service;

import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sunny on 2017/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findAll() throws Exception {
        List<User> roleList = userService.findAll();
        for (User u :
                roleList) {
            System.out.println(u);
        }
    }

    @Test
    public void findById() {
        User user = userService.findById(1);
        System.out.println(user);
    }

    @Test
    public void findByName() {
        User user = userService.findByUserName("admin");
        System.out.println(user);
    }
}

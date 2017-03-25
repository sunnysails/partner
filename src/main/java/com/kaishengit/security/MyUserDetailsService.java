package com.kaishengit.security;

import com.google.common.collect.Lists;
import com.kaishengit.dto.MyUserDetails;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunny on 2017/3/24.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findByUserName(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if (user == null) {
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
//                List<Role> roles = userService.findRoleByUserId(user.getId());
                Role role = userService.findRoleByUserId(user.getId());
                List<Role> roleList = Lists.newArrayList();
                roleList.add(role);
                //TODO
                return new MyUserDetails(user, roleList);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }

    public void addLog(String ip,Integer userId) {
        userService.addLoginLog(ip,userId);
    }
}

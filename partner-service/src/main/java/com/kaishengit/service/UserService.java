package com.kaishengit.service;

import com.kaishengit.pojo.User;

import java.util.List;

/**
 * Created by sunny on 2017/3/15.
 */
public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    User findByUserName(String userName);
}

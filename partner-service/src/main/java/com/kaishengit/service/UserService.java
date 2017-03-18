package com.kaishengit.service;

import com.kaishengit.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by sunny on 2017/3/15.
 */
public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    User findByUserName(String userName);

    void save(User user);

    void resetUserPassword(Integer id);

    Long count();

    List<User> findLimit(Integer start, Integer length);

    void update(User user);

    void updateNoPassword(User user);

    List<User> findLimitUserOrRealName(Integer start, Integer length, String name);
}

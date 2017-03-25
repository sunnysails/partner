package com.kaishengit.service;

import com.kaishengit.dto.MyUserDetails;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;

import java.util.List;
import java.util.Set;


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

    void settingUserPassword(String oldPassword, String newPassword);

    Long countWithUser();

    void addLoginLog(String ip, Integer userId);

    Set<UserLog> findUserLoginLog();

    List<UserLog> findUserLoginLog(Integer start, Integer length);

    Role findRoleByUserId(Integer id);

}

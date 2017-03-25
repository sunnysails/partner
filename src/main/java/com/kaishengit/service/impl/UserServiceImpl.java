package com.kaishengit.service.impl;

import com.kaishengit.dto.MyUserDetails;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserLogMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;
import com.kaishengit.security.SecurityUtil;
import com.kaishengit.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by sunny on 2017/3/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLogMapper logMapper;
    @Value("${password.salt}")
    private String salt;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public void save(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword() + salt));
        userMapper.save(user);
    }

    @Override
    public void resetUserPassword(Integer id) {
        User user = userMapper.findById(id);
        user.setPassword(DigestUtils.md5Hex(User.PASSWORD0 + salt));
        userMapper.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return userMapper.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findLimit(Integer start, Integer length) {
        return userMapper.findPage(start, length);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateNoPassword(User user) {
        userMapper.update(user);
    }

    /**
     * 根据开始Id及查询总长度和Name分配查询方式
     *
     * @param start
     * @param length
     * @param name
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> findLimitUserOrRealName(Integer start, Integer length, String name) {
        name = "%" + name + "%";
        return userMapper.findPageUserOrRealName(start, length, name);
    }

    @Override
    public void settingUserPassword(String oldPassword, String newPassword) {
        User user = SecurityUtil.getCurrentUser();
        if (user.getPassword().equals(DigestUtils.md5Hex(oldPassword + salt))) {
            user.setPassword(DigestUtils.md5Hex(newPassword + salt));
            userMapper.update(user);
        } else {
            throw new ServiceException("原始密码错误，修改失败");
        }
    }

    @Override
    public Long countWithUser() {
        //TODO
        return null;
//        return Long.valueOf(userMapper.findById(ShiroUtil.getCurrentUserId()).getUserLogList().size());
    }

    @Override
    public void addLoginLog(String ip,Integer userId) {
        logMapper.save(new UserLog(ip, userId));
    }

    @Override
    public Set<UserLog> findUserLoginLog() {
        //TODO
        return null;
//        return userMapper.findById(ShiroUtil.getCurrentUserId()).getUserLogList();
    }

    @Override
    public List<UserLog> findUserLoginLog(Integer start, Integer length) {
        Integer id = 1;
        return logMapper.findByUserIdWithPage(start, length, id);
    }

    @Override
    public Role findRoleByUserId(Integer id) {
        return userMapper.findRoleByUserId(id);
    }

}
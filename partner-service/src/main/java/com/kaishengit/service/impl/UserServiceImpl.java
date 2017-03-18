package com.kaishengit.service.impl;

import com.kaishengit.dao.son.UserDao;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by sunny on 2017/3/15.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Value("${password.salt}")
    private String salt;

    /**
     * 给密码加盐
     *
     * @param user 需要加盐的User对象
     */
    private void addSalt(User user) {
        if (user.getPassword() != null) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword() + salt));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void save(User user) {
        addSalt(user);
        userDao.saveOrUpdate(user);
    }

    @Override
    public void resetUserPassword(Integer id) {
        User user = userDao.findById(id);
        user.setPassword(DigestUtils.md5Hex(User.PASSWORD0 + salt));
        userDao.saveOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return userDao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findLimit(Integer start, Integer length) {
        return userDao.findLimit(start, length);
    }

    @Override
    public void update(User user) {
        userDao.saveOrUpdate(user);
    }

    @Override
    public void updateNoPassword(User user) {
        userDao.updateNotNull(user);
    }


    @Override
    public List<User> findLimitUserOrRealName(Integer start, Integer length, String name) {
        if (name.isEmpty()) {
            return userDao.findLimit(start, length);
        } else {
            return userDao.findLimitUserOrRealName(start, length, name);
        }
    }

}

package com.kaishengit.service.impl;

import com.kaishengit.dao.son.UserDao;
import com.kaishengit.dao.son.UserLogDao;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;
import com.kaishengit.service.UserService;
import com.kaishengit.shiro.ShiroUtil;
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
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLogDao logDao;
    @Value("${password.salt}")
    private String salt;

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
        user.setPassword(DigestUtils.md5Hex(user.getPassword() + salt));
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
        if (name.isEmpty()) {
            return userDao.findLimit(start, length);
        } else {
            return userDao.findLimitUserOrRealName(start, length, name);
        }
    }

    @Override
    public void settingUserPassword(String oldPassword, String newPassword) {
        User user = ShiroUtil.getCurrentUser();
        if (user.getPassword().equals(DigestUtils.md5Hex(oldPassword + salt))) {
            user.setPassword(DigestUtils.md5Hex(newPassword + salt));
            userDao.update(user);
        } else {
            throw new ServiceException("原始密码错误，修改失败");
        }
    }

    @Override
    public Long countWithUser() {
        return Long.valueOf(userDao.findById(ShiroUtil.getCurrentUserId()).getUserLogList().size());
    }

    @Override
    public void addLoginLog(String ip) {
        Integer userId = ShiroUtil.getCurrentUserId();
        UserLog userLog = new UserLog(ip, userId);
        logDao.save(userLog);
    }

    @Override
    public Set<UserLog> findUserLoginLog() {
        return userDao.findById(ShiroUtil.getCurrentUserId()).getUserLogList();
    }

    @Override
    public List<UserLog> findUserLoginLog(Integer start, Integer length) {
        return logDao.findByUserIdWithPage(start, length, ShiroUtil.getCurrentUserId());
    }

}

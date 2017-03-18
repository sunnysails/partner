package com.kaishengit.dao.son;

import com.kaishengit.dao.BaseDao;
import com.kaishengit.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by sunny on 2017/3/15.
 */
@Repository
public class UserDao extends BaseDao<User> {

    public User findByUserName(String userName) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));
        return (User) criteria.uniqueResult();
    }

    public void updateNotNull(User user) {
        User user1 = findById(user.getId());
        user1.setUserName(user.getUserName() == null ? user1.getUserName() : user.getUserName());
        user1.setPassword(user.getPassword() == null ? user1.getPassword() : user.getPassword());
        user1.setRealName(user.getRealName() == null ? user1.getRealName() : user.getRealName());
        user1.setWeiXin(user.getWeiXin() == null ? user1.getWeiXin() : user.getWeiXin());
        user1.setRole(user.getRole().getId() == null ? user1.getRole() : user.getRole());
        user1.setEnable(user.getEnable() == null ? user1.getEnable() : user.getEnable());
        update(user1);
    }
}

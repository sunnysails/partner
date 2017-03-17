package com.kaishengit.dao;

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
        criteria.add(Restrictions.eq("userName",userName));
        return (User) criteria.uniqueResult();
    }
}

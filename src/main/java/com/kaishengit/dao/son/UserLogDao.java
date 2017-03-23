package com.kaishengit.dao.son;

import com.kaishengit.dao.BaseDao;
import com.kaishengit.pojo.UserLog;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunny on 2017/3/21.
 */
@Repository
public class UserLogDao extends BaseDao<UserLog> {

    public List<UserLog> findByUserIdWithPage(Integer start, Integer length, Integer userId) {
        Criteria criteria = getSession().createCriteria(UserLog.class);
        criteria.setFirstResult(start);
        criteria.setMaxResults(length);
        criteria.addOrder(Order.desc("loginTime"));
        criteria.add(Restrictions.eq("user.id",userId));
        return criteria.list();
    }
}

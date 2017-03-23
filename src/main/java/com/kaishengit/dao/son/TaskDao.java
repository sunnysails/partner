package com.kaishengit.dao.son;

import com.kaishengit.dao.BaseDao;
import com.kaishengit.pojo.Task;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunny on 2017/3/22.
 */
@Repository
public class TaskDao extends BaseDao<Task> {
    /*    public List<Task> findStartEndWithUser(String start, String end, Integer userId) {
            Criteria criteria = getSession().createCriteria(Task.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("start", start + "%"));
            disjunction.add(Restrictions.like("end", end + "%"));
            criteria.add(disjunction);
            criteria.add(Restrictions.eq("user.id", userId));
            return criteria.list();
        }*/
    public List<Task> findStartEndWithUser(String start, String end, Integer userId) {
        Criteria criteria = getSession().createCriteria(Task.class);
        criteria.add(Restrictions.ge("start", start));
        criteria.add(Restrictions.le("end", end));
        criteria.add(Restrictions.eq("user.id", userId));
        return criteria.list();
    }
}

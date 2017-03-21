package com.kaishengit.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by sunny on 2017/3/16.
 */
public class BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class clazz;

    //获取和当前线程绑定的Seesion
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public BaseDao() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(clazz);
        return criteria.list();
    }

    public List<T> findAll(String propertyName, String orderType) {
        Criteria criteria = getSession().createCriteria(clazz);
        if ("desc".equals(orderType)) {
            criteria.addOrder(Order.desc(propertyName));
        } else {
            criteria.addOrder(Order.asc(propertyName));
        }
        return criteria.list();
    }

    public T findById(Integer id) {
        return (T) getSession().get(clazz, id);
    }

    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void delete(Integer id) {
        getSession().delete(findById(id));
    }
}

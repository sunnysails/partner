package com.kaishengit.dao;

import com.kaishengit.pojo.Doc;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
@Repository
public class DocDao extends BaseDao<Doc> {

    public List<Doc> findByFid(Integer fid) {

        String hql = "from Doc where fid = :fid";
        Query query = getSession().createQuery(hql);
        query.setParameter("fid",fid);
        return query.list();
    }
}

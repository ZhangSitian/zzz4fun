package com.zzz.learn.mybatis.dao.hibernate;

import com.alibaba.fastjson.JSONObject;
import com.zzz.learn.mybatis.dao.ProductDao;
import com.zzz.learn.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("hibernateProductDao")
@Transactional
public class HibernateProductDao implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int insert(Product product) {
        System.out.println(JSONObject.toJSONString(product));
        currentSession().save(product);
        return 1;
    }

    @Override
    public Product selectById(long id) {
        System.out.println(id);
        return (Product) currentSession().get(Product.class,id);
    }

    @Override
    public int add(Product product) {
        System.out.println(JSONObject.toJSONString(product));
        currentSession().saveOrUpdate(product);
        return 1;
    }
}

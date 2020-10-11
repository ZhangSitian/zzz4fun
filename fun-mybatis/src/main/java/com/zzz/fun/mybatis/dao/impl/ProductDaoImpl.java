package com.zzz.fun.mybatis.dao.impl;

import com.zzz.fun.mybatis.dao.ProductDao;
import com.zzz.fun.mybatis.dao.domain.Product;
import com.zzz.fun.mybatis.dao.domain.ProductQueryVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    // 需要向dao实现类中注入SqlSessionFactory
    // 这里通过构造方法注入
    private SqlSessionFactory sqlSessionFactory;

    public ProductDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public Product findProductById(long id)  {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Product product = sqlSession.selectOne("test.findProductById",id);
        //释放资源
        sqlSession.close();
        return product;
    }

    @Override
    public List<Product> findProductByName(String name)  {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Product> list = sqlSession.selectList("test.findProductByName", name);
        // 释放资源
        sqlSession.close();
        return list;
    }

    @Override
    public void insertProduct(Product product)  {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行插入操作
        sqlSession.insert("test.insertProduct", product);
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteProduct(long id)  {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行插入操作
        sqlSession.delete("test.deleteProduct", id);
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Override
    public  List<Product> findProductByVO(ProductQueryVo productQueryVo)  {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Product> selectList = sqlSession.selectList("test.findProductById",productQueryVo.getProduct().getId());
        //释放资源
        sqlSession.close();
        return selectList;
    }

    @Override
    public int countProduct(ProductQueryVo productQueryVo) {
        return 0;
    }

}

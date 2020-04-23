package com.zzz.learn.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@EnableAutoConfiguration
@MapperScan("com.zzz.learn.dao")
public class ShardingConfig {

    @Bean
    public DruidDataSource abstractDataSourceLocal(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/sharding_0?useUnicode=true&characterEncoding=utf-8&useSSL=true");//防止乱码
        dataSource.setUsername("root");
        dataSource.setPassword("!QAZ2wsx");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(30);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(abstractDataSourceLocal());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(abstractDataSourceLocal());
        sessionFactory.setTypeAliasesPackage("com.zzz.learn.domain");
        return sessionFactory;
    }


}

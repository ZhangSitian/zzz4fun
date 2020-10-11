package com.zzz.fun.mybatis.generate1;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    @Test
    public void generate() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overWrite = true;
        //指定逆向工程配置文件
        String resource = "mybatis-generator-1.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(inputStream);
//        -------------------开始配置
        Context context = config.getContext("testTables");
        JDBCConnectionConfiguration jdbcConnectionConfiguration = context.getJdbcConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&useSSL=false");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.setPassword("");
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = context.getJavaModelGeneratorConfiguration();
        // 生成PO类的位置
        javaModelGeneratorConfiguration.setTargetPackage("com.zzz.learn.mybatis.generate1.po");
        // mapper映射文件生成的位置
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = context.getSqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetPackage("mapper.generate1");
        // mapper接口生成的位置
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = context.getJavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage("com.zzz.learn.mybatis.generate1.mapper");
        // 添加要生成的表
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("items");
        context.addTableConfiguration(tableConfiguration);
        //        --------------------配置结束
        DefaultShellCallback callback = new DefaultShellCallback(overWrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}

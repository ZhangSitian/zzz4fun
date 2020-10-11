package com.zzz.fun.mybatis.generate2;

import org.junit.Test;

public class GeneratorTest {

    @Test
    public void test() throws Exception {
        MybatisGenerator.create().setURL("jdbc:mysql://127.0.0.1:3306/test?useSSL=false").setUsername("root").setPassword("")
                .setTableName("product").setBasePackage("com.zzz.learn.mybatis.generate2").generate();
    }

}

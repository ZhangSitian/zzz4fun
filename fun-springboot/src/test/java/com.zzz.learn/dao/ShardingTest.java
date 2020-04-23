package com.zzz.learn.dao;

import com.zzz.learn.domain.Blog;
import com.zzz.learn.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingConfig.class)
public class ShardingTest {

    UserMapper userMapper;

    @Before
    public void before(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ShardingConfig.class);
        userMapper = context.getBean(UserMapper.class);
    }

    @Test
    public void testFindStudent() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("zhangsan");
        user.setAge(10);
        userMapper.insert(user);
    }

    @Test
    public void testFindStudent2() throws Exception {
//        studentDao.queryCount();
    }
}

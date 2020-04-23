package com.zzz.learn.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoubleSourceConfig.class)
public class DoubleSourceRepositoryTest {
    @Autowired
    StudentDao studentDao;
    @Test
    public void savePrimary() throws Exception {
        studentDao.savePrimary();
    }

    @Test
    public void saveSecond() throws Exception {
        studentDao.saveSecond();
    }

    @Test
    public void queryCountPrimary() throws Exception {
        System.out.println(studentDao.queryCountPrimary());
    }

    @Test
    public void queryCountSecond() throws Exception {
        System.out.println(studentDao.queryCountSecond());
    }

}

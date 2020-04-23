package com.zzz.learn.controller;

import com.zzz.learn.AppConfig;
import com.zzz.learn.dao.StudentDao;
import com.zzz.learn.domain.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * MongoTemplate测试类
 * @author yinjihuan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class MongoTemplateTest {
    @Autowired
    StudentDao studentDao;
    @Value("${server.port}")
    private String port;
    @Value("${server.context-path}")
    private String contextPath;
    private String getBasePath() {
        return "http://localhost" + contextPath;
    }
    private TestRestTemplate restTemplate = new TestRestTemplate();
    @Test
    public void testFindStudent() throws Exception {
        Assert.assertNotNull(studentDao.findByName("yinjihuan"));
    }
    @Test
    public void testFindByName() {
        ResponseEntity<Student> resp =
                restTemplate.getForEntity(getBasePath() + "students/{name}", Student.class, "yinjihuan");
        Student student = resp.getBody();
        assertEquals("yinjihuan", student.getName());
    }
}

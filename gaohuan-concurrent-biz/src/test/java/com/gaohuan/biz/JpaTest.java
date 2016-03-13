package com.gaohuan.biz;

import com.gaohuan.biz.dao.UserRepository;
import com.gaohuan.biz.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by gh on 2016/3/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTest {
    @Autowired
    UserRepository userRepository;

    @Before
    public void init() {
        System.out.println("init");
    }

    @Test
    public void test1() {
        List<User> userList = userRepository.findAll();
        System.out.println(userList.toString());
    }

    @Test
    public void test2() {
        List<User> list = userRepository.findByEmailAddress("gaohuan0904@163.com");
        Assert.assertNotNull(list);
    }

    @Test
    public void test3() {
        List<User> list = userRepository.findByNameEndsWith("huan");
        Assert.assertNotNull(list);
    }


}

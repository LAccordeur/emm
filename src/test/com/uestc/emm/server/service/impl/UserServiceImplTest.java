package com.uestc.emm.server.service.impl;

import com.uestc.emm.server.entity.User;
import com.uestc.emm.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setUserName("测试2");
        user.setPassword("123456");
        user.setPhone("18936752871");
        System.out.println(userService.register(user));
    }

}
package com.uestc.emm.server.dao;

import com.uestc.emm.server.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void insertSelective() throws Exception {

        //System.out.println(userMapper.insertSelective());
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
    }

    @Test
    public void selectByEntity() throws Exception {
    }

    @Test
    public void selectByPhone() throws Exception {
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
    }

}
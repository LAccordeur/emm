package com.uestc.emm.server.service.impl;

import com.uestc.emm.server.core.dto.Response;
import com.uestc.emm.server.core.dto.commend.LoginCommend;
import com.uestc.emm.server.core.im.NeteaseImHelper;
import com.uestc.emm.server.core.jwt.JwtHelper;
import com.uestc.emm.server.dao.UserMapper;
import com.uestc.emm.server.entity.User;
import com.uestc.emm.server.service.UserService;
import com.uestc.emm.server.util.ValidationUtil;
import com.uestc.emm.server.util.assembler.UserAssembler;
import com.uestc.emm.server.util.common.UUIDUtil;
import com.uestc.emm.server.util.security.CodecUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : guoyang
 * @Description : 处理用户相关的操作
 * @Date : Created on 2017/11/22
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserMapper userMapper;

    /**
     * 注册
     * @param user
     * @return
     */
    public Response register(User user) {
        Response response = new Response();

        String encryptPassword = CodecUtil.encryptWithSHA256(user.getPassword() + user.getPhone());
        user.setPassword(encryptPassword);
        String id = UUIDUtil.get32UUIDLowerCase();
        user.setId(id);

        ValidationUtil.getInstance().validateParams(user);
        //创建网易云通信id
        String resultId = NeteaseImHelper.createToken(id, user.getUserName());
        if (resultId == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMsg("注册失败");
        }

        //保存返回的token
        user.setToken(resultId);

        if (userMapper.insertSelective(user) > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMsg("注册成功");
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMsg("注册失败");
        }

        return response;
    }

    /**
     * 登录
     * @param commend
     * @return
     */
    public Response login(LoginCommend commend) {
        Response response = new Response();


        ValidationUtil.getInstance().validateParams(commend);
        User user = UserAssembler.toUser(commend);
        //1.app服务器登录验证
        if (userMapper.selectByPhone(user.getPhone()) == null) {
            //1.账号不存在
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMsg("账号不存在");
            response.setData(null);
            return response;

        } else {
            //2.账号存在
            String encryptPassword = CodecUtil.encryptWithSHA256(user.getPassword() + user.getPhone());
            user.setPassword(encryptPassword);
            User userResult = userMapper.selectByEntity(user);
            if (userResult == null) {
                //账号密码不匹配
                response.setCode(HttpStatus.FORBIDDEN.value());
                response.setMsg("账号和密码不匹配");
                response.setData(null);
            } else {


                //账号密码正确
                response.setCode(HttpStatus.OK.value());
                response.setMsg("登录成功");

                //生成token，用以后续验证登录状态
                String token = getToken(userResult);
                Map<String, Object> result = new HashMap<String, Object>(2);
                result.put("token", token);
                result.put("user", userResult);

                response.setData(result);

            }

        }


        return response;
    }

    public Response updateUser(User user) {

        Response response = new Response();

        user.setPassword(null);
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0) {
            return new Response(HttpStatus.OK.value(), "更新成功");
        } else {
            return new Response(HttpStatus.FORBIDDEN.value(), "更新失败");
        }

    }

    public Response getUserInfoById(String id) {
        Response response = new Response();


        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMsg("用户信息");
            response.setData(user);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMsg("用户不存在");
            response.setData(null);
        }

        return response;
    }

    public Response getUserInfoByPhone(String phone) {
        Response response = new Response();


        User user = userMapper.selectByPhone(phone);
        if (user != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMsg("查询用户存在");
            response.setData(user);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMsg("用户不存在");
            response.setData(null);
        }


        return response;
    }

    /**
     * 查看手机号是否被注册
     *
     * @param phone
     * @return
     */
    public Response checkPhone(String phone) {
        Response response = new Response(HttpStatus.UNAUTHORIZED.value(), "查询用户失败");


        User user = userMapper.selectByPhone(phone);
        if (user != null) {
            response.setCode(HttpStatus.CONFLICT.value());
            response.setMsg("该手机号已被注册");

        } else {
            response.setCode(HttpStatus.OK.value());
            response.setMsg("该手机号可以注册");

        }

        response.setData(null);
        return response;
    }

    private String getToken(User user) {
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("uid", user.getId()); //用户ID
        payload.put("iat", date.getTime()); //生成时间
        payload.put("ext", date.getTime() + 1000 * 60 * 60); //过期时间1小时
        String token = JwtHelper.createToken(payload);

        return token;
    }
}

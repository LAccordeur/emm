package com.uestc.emm.server.controller;

import com.uestc.emm.server.core.dto.Response;
import com.uestc.emm.server.core.dto.commend.LoginCommend;
import com.uestc.emm.server.entity.User;
import com.uestc.emm.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @Author : guoyang
 * @Description :
 * @Date : Created on 2017/11/22
 */
@Controller
@RequestMapping(value = "/api", produces = {"application/json;charset=utf8"})
public class UserController {
    @Inject
    private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(e);
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestBody LoginCommend commend) {
        try {
            return userService.login(commend);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(e);
        }
    }

    @RequestMapping(value = "/phone/check", method = RequestMethod.GET)
    @ResponseBody
    public Response checkPhone(String phone) {
        try {
            return userService.checkPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(e);
        }
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateUserInfo(@RequestBody User user) {
        try {
            return userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(e);
        }
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    @ResponseBody
    public Response getUserById(String id) {
        try {
            return userService.getUserInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(e);
        }
    }
}

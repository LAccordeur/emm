package com.uestc.emm.server.service;

import com.uestc.emm.server.core.dto.Response;
import com.uestc.emm.server.core.dto.commend.LoginCommend;
import com.uestc.emm.server.entity.User;

/**
 * @Author : guoyang
 * @Description :
 * @Date : Created on 2017/11/22
 */
public interface UserService {

    Response register(User user);

    Response login(LoginCommend commend);

    Response updateUser(User user);

    Response getUserInfoById(String id);

    Response getUserInfoByPhone(String phone);

    Response checkPhone(String phone);
}

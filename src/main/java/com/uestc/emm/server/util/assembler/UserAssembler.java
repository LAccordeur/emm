package com.uestc.emm.server.util.assembler;


import com.uestc.emm.server.core.dto.commend.LoginCommend;
import com.uestc.emm.server.entity.User;
import com.uestc.emm.server.util.common.StringUtil;

/**
 * @Author : guoyang
 * @Description :
 * @Date : Created on 2017/10/26
 */
public final class UserAssembler {

    /**
     * 将登录命令中的属性拷贝至User对象中
     * @param loginCommend
     * @return
     */
    public static User toUser(LoginCommend loginCommend) {
        User user = new User();

        if (loginCommend != null && StringUtil.isNotEmpty(loginCommend.getPassword()) && StringUtil.isNotEmpty(loginCommend.getPhone())) {
            user.setPhone(loginCommend.getPhone());
            user.setPassword(loginCommend.getPassword());
        }
        return user;
    }


}

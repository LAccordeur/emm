package com.uestc.emm.server.core.dto.commend;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginCommend {

    @NotNull(message = "登录账号需为11位手机号")
    @Size(min = 11, max = 11)
    private String phone;

    @NotNull(message = "密码不可为空")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.uestc.emm.server.util.common;

import java.util.UUID;

/**
 * @Author : guoyang
 * @Description :
 * @Date : Created on 2017/11/22
 */
public final class UUIDUtil {

    public static String get32UUIDLowerCase() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(get32UUIDLowerCase());
    }
}

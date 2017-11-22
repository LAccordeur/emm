package com.uestc.emm.server.util.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by LAccordeur on 2017/9/4.
 * 字符串工具类
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (string != null) {
            string = string.trim();
        }
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断字符串是否非空
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static String[] splitString(String source, String reg) {
        return StringUtils.split(source, reg);
    }
}

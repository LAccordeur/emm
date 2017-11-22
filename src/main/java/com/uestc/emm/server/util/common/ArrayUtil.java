package com.uestc.emm.server.util.common;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by LAccordeur on 2017/9/10.
 */
public final class ArrayUtil {

    static {
        System.out.println("ArrayUtil init");
    }

    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}

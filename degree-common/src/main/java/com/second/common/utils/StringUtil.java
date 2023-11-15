package com.second.common.utils;

import com.second.common.consts.BaseConstants;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/21
 * {@code @description} string utils
 */
public class StringUtil {

    /**
     * check if null or empty string
     */
    public static boolean isNullOrEmpty(Object obj) {
        return (null == obj
                || BaseConstants.EMPTY_STRING.equals(obj)
                || BaseConstants.NULL_STRING.equals(obj));
    }

    /**
     * check if null or empty string
     */
    public static boolean isNotNullOrEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }
}

package com.second.common.util;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/21
 * {@code @description} string utils
 */
public class StringUtils {

    /**
     * check if null or empty string
     */
    public static boolean isNullOrEmpty(Object obj) {
        return (null == obj
                || BaseDictCode.EMPTY_STRING.equals(obj)
                || BaseDictCode.NULL_STRING.equals(obj));
    }

    /**
     * check if null or empty string
     */
    public static boolean isNotNullOrEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }
}

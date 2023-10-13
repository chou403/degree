package com.second.common.util;

import java.math.BigDecimal;

/**
 * {@code @author} JSY
 * {@code @date} 2023/2/6
 * {@code @className} commonUtil
 * {@code @description} 记录常用 util
 */
public class CommonUtil {

    /**
     * compare version
     */
    public static int compareVersion(String v1, String v2) {
        int n = v1.length(), m = v2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            for (; i < n && v1.charAt(i) != '.'; ++i) {
                x = x * 10 + v1.charAt(i) - '0';
            }
            ++i; // 跳过点号
            int y = 0;
            for (; j < m && v2.charAt(j) != '.'; ++j) {
                y = y * 10 + v2.charAt(j) - '0';
            }
            ++j; // 跳过点号
            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }

    /**
     * check if equal
     */
    public static boolean isEquals(Object obj, Object expectValue) {
        if (StringUtils.isNullOrEmpty(obj) || StringUtils.isNullOrEmpty(expectValue)) {
            return false;
        }
        return expectValue.equals(obj);
    }

    /**
     * obj to Boolean
     */
    public static boolean valueOfBoolean(Object obj) {
        return Boolean.parseBoolean(valueOfString(obj));
    }

    /**
     * obj to String , default EMPTY_STRING
     */
    public static String valueOfString(Object obj) {
        if (StringUtils.isNullOrEmpty(obj)) {
            return BaseDictCode.EMPTY_STRING;
        }
        return String.valueOf(obj);
    }

    /**
     * obj to BigDecimal , default 0
     */
    public static BigDecimal valueOfBigDecimal(Object obj) {
        if (StringUtils.isNullOrEmpty(obj)) {
            return BigDecimal.ZERO;
        }
        if (isNumeric(obj)) {
            return new BigDecimal(String.valueOf(obj));
        }
        return BigDecimal.ZERO;
    }

    /**
     * obj to Double , default 0
     */
    public static Double valueOfDouble(Object obj) {
        if (StringUtils.isNullOrEmpty(obj)) {
            return BaseDictCode.DOUBLE_ZERO;
        }
        if (isNumeric(obj)) {
            return Double.parseDouble(String.valueOf(obj));
        }
        return BaseDictCode.DOUBLE_ZERO;
    }

    /**
     * obj to Float , default 0
     */
    public static Float valueOfFloat(Object obj) {
        if (StringUtils.isNullOrEmpty(obj)) {
            return BaseDictCode.FLOAT_ZERO;
        }
        if (isNumeric(obj)) {
            return Float.parseFloat(String.valueOf(obj));
        }
        return BaseDictCode.FLOAT_ZERO;
    }

    /**
     * obj to Integer , default 0
     */
    public static Integer valueOfInteger(Object obj) {
        if (StringUtils.isNullOrEmpty(obj)) {
            return BaseDictCode.INTEGER_ZERO;
        }
        if (isNumeric(obj)) {
            return Integer.parseInt(String.valueOf(obj));
        }
        return BaseDictCode.INTEGER_ZERO;
    }

    /**
     * obj to Long , default 0L
     */
    public static Long valueOfLong(Object obj) {
        if (StringUtils.isNullOrEmpty(obj)) {
            return BaseDictCode.LONG_ZERO;
        }
        if (isNumeric(obj)) {
            return Long.parseLong(String.valueOf(obj));
        }
        return BaseDictCode.LONG_ZERO;
    }

    /**
     * check obj if Numeric type
     */
    public static boolean isNumeric(Object obj) {
        try {
            Double.parseDouble(valueOfString(obj));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

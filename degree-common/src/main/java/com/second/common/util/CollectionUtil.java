package com.second.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/8/22
 * {@code @description} collection utils
 */
public class CollectionUtil {

    /**
     * check collection if empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * check collection if not empty
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * check map if empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        //return MapUtils.isEmpty(map);
        return map == null || map.isEmpty();
    }

    /**
     * check map if not empty
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}

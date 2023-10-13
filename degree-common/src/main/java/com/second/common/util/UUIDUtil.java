package com.second.common.util;

import java.util.UUID;

public class UUIDUtil {

    public static String creatUUID() {
        return UUID.randomUUID().toString().replace(BaseDictCode.ACROSS_SPRING_STRING, BaseDictCode.EMPTY_STRING);
    }

}

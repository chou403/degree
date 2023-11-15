package com.second.common.utils;

import com.second.common.consts.BaseConstants;

import java.util.UUID;

public class UUIDUtil {

    public static String creatUUID() {
        return UUID.randomUUID().toString().replace(BaseConstants.ACROSS_SPRING_STRING, BaseConstants.EMPTY_STRING);
    }

}

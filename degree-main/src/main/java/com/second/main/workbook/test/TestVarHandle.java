package com.second.main.workbook.test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/14
 * {@code @description} test varHandle
 */
public class TestVarHandle {

    private static final VarHandle VALUE;
    private volatile int num;

    static {
        try {
            VALUE = MethodHandles.lookup().findVarHandle(TestVarHandle.class, "num", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error((e));
        }
    }

    public static void main(String[] args) {

    }

}

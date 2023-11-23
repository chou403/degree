package com.second.project.test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/14
 * {@code @description} test varHandle
 */
public class TestVarHandle {

    private static final VarHandle x;

    static {
        try {
            x = MethodHandles.lookup().findStaticVarHandle(TestVarHandle.class, "x", VarHandle.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error((e));
        }
    }

    public static void main(String[] args) {
        System.out.println(x);
    }

}

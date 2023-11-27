package com.second.main.workbook.reentrantLock;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 自定义类 加锁/释放锁
 * {@code @author}  chouchou
 * {@code @date}    2023/4/10 17:37
 */
@Slf4j
public class TestLock {

    /**
     * 标识 加锁成功=1 自由状态=0
     */
    static Unsafe unsafe;

    /**
     * unsafe 对象
     */
    volatile int state = 0;

    /**
     * state 这个字段在ShadowLock对象当中的内存偏移量
     */
    static long stateOffset;

    /*
      1、获取unsafe对象
      2、通过unsafe对象得到state属性在当前对象中的偏移地址
    */
    static {
        try {
            unsafe = getUnsafe();
            stateOffset = unsafe.objectFieldOffset(TestLock.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加锁
     */
    public void lock() {
        // compareAndSet(0, 1) 当某个属性（state）的值如果为0 则修改为1
        // 效率高 cas 无锁 不会发生内核态切换
        // 这里保证了原子性操作
        while (!compareAndSet(0, 1)) {
            // 加锁失败
            log.info("当前线程：{}在等待获取锁", Thread.currentThread().getName());
        }
        // 如果成功 则进行赋值
        Thread currentThread = Thread.currentThread();
        log.info("当前线程：{}获取了锁", Thread.currentThread().getName());
    }

    /**
     * 释放锁
     */
    public void unlock() {
        log.info("当前线程：{}释放了锁", Thread.currentThread().getName());
        state = 0;
    }

    /**
     * 获取 unsafe 对象
     */
    private static Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }

    /**
     * cas 修改 state的值
     * expect 预期值 如果相同则把 state 的值改成x 原子操作
     */
    private boolean compareAndSet(int expect, int x) {
        // stateOffset 一个地址 （state属性的地址）
        return unsafe.compareAndSwapInt(this, stateOffset, expect, x);
    }
}

package com.second.common.handle;

import java.util.function.Consumer;

/**
 * 空值与非空值分支处理
 * {@code @author}  chouchou
 * {@code @date}    2023/4/23 17:53
 */
@FunctionalInterface
public interface PresentOrElseHandler<T extends Object> {

    /**
     * 值不为空时执行消费操作
     * 值为空时执行其他操作
     * {@code @date} 2023/4/23 17:55
    */
    void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);
}

package com.second.common.handle;

/**
 * if 分支操作
 * {@code @author}  chouchou
 * {@code @date}    2023/4/23 17:48
 */
@FunctionalInterface
public interface BranchHandle {

    /**
     * 分支操作
     * {@code @date} 2023/4/23 17:44
    */
    void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);
}

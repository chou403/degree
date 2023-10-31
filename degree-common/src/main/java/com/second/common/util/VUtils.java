package com.second.common.util;

import com.second.common.handle.BranchHandle;
import com.second.common.handle.PresentOrElseHandler;
import com.second.common.handle.ThrowExceptionFunction;

/**
 * 方法使用判断
 * {@code @author}  chouchou
 * {@code @date}    2023/4/23 17:45
 */
public class VUtils {

    /**
     * 如果参数为true抛出异常
     * {@code @date} 2023/4/23 17:47
    */
    public static ThrowExceptionFunction isTrue(boolean b) {
        return (errorMessage)->{
            if (b) {
                throw new RuntimeException(errorMessage);
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     * {@code @date} 2023/4/23 17:52
    */
    public static BranchHandle isTrueOrFalse(boolean b) {
        return (trueHandle, falseHandle)->{
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     * {@code @date} 2023/4/23 17:57
    */
    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {
        return (consumer, runnable)->{
            if (str == null || str.length() == 0) {
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }
}

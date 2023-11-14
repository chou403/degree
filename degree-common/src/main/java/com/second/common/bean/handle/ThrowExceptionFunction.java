package com.second.common.bean.handle;

/**
 * 抛异常接口
 * {@code @author}  chouchou
 * {@code @date}    2023/4/23 17:43
 */
@FunctionalInterface
public interface ThrowExceptionFunction {
    /**
     * 抛出异常信息
     * {@code @date} 2023/4/23 17:44
     * @param	message 异常信息
    */
    void throwMessage(String message);
}

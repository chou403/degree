package com.second.main.constraint;


import com.second.common.excel.ExplicitInterface;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/28
 * {@code @description} 旧件检查下拉
 */
public class GenderExplicitConstraint implements ExplicitInterface {
    /**
     * 下拉列表的内容数组
     */
    @Override
    public String[] source() {
        return new String[]{"男","女","未知"};
    }
}

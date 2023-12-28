package com.second.common.excel;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/28
 * {@code @description} 旧件检查下拉
 */
public class PartCheckExplicitConstraint implements ExplicitInterface {
    /**
     * 下拉列表的内容数组
     */
    @Override
    public String[] source() {
        return new String[]{"合格","不合格","缺件"};
    }
}

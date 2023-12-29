package com.second.common.bean;

/**
 * {@code @author} chou401
 * {@code @date} 2022/12/14
 * {@code @className} BusinessTypeEnum
 * {@code @description} 切面日志枚举
 */
public enum BusinessTypeEnum {
    /**
     * 其它
     */
    OTHER,
    /**
     * 新增
     */
    INSERT,
    /**
     * 修改
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 授权
     */
    GRANT,
    /**
     * 导出
     */
    EXPORT,
    /**
     * 导入
     */
    IMPORT,
    /**
     * 强退
     */
    FORCE,
    /**
     * 生成代码
     */
    GENCODE,
    /**
     * 清空数据
     */
    CLEAN,
}

package com.second.main.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.second.common.aop.annotations.ExplicitConstraint;
import com.second.common.excel.BaseEasyExcel;
import com.second.main.constraint.GenderExplicitConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/28
 * {@code @description}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExcelTestDTO extends BaseEasyExcel {

    @ExcelProperty("昵称")
    private String name;
    @ExcelProperty("账号")
    private String code;
    @ExcelProperty("性别")
    @ExplicitConstraint(sourceClass = GenderExplicitConstraint.class)
    private String gender;
    @ExcelProperty("年龄")
    private String age;
}

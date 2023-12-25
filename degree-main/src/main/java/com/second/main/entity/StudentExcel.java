package com.second.main.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.second.common.aop.annotations.ExcelValid;
import com.second.common.aop.annotations.NumberValid;
import com.second.main.utils.GenderConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/7
 * {@code @description}
 */
@Data
public class StudentExcel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("账号")
    @ColumnWidth(20)
    @ExcelValid(message = "账号不能有空数据！")
    private String userCode;

    @ExcelProperty("用户名")
    @ColumnWidth(20)
    @ExcelValid(message = "用户名不能有空数据！")
    private String userName;

    @ExcelProperty("昵称")
    @ColumnWidth(20)
    @ExcelValid(message = "昵称不能有空数据！")
    private String nickName;

    @ExcelProperty("年龄")
    @ColumnWidth(20)
    @NumberValid(message = "年龄不得小于0")
    private Integer age;

    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    @ColumnWidth(20)
    private Integer gender;

    @ExcelProperty("日期")
    @ColumnWidth(20)
    private Date createTime;
}

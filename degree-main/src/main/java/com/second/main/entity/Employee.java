package com.second.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} employee
 */
@ToString
@Data
@TableName("employee")
public class Employee {

    private Integer employeeId;
    private Integer age;
    private String employeeName;
    private Date birthDate;
    private Integer gender;
    private String idNumber;
    private Date createTime;
    private Date updateTime;
    private Integer status;

}

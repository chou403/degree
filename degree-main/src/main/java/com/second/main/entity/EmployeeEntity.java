package com.second.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * {@code @author} chou401
 * @since 2023-12-06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("employee")
@Schema(name = "EmployeeEntity", description = "人员表")
public class EmployeeEntity extends Model<EmployeeEntity> {

    @TableId(value = "employee_id", type = IdType.AUTO)
    private Integer employeeId;

    @TableField("age")
    private Integer age;

    @TableField("employee_name")
    private String employeeName;

    @TableField("birth_date")
    private Date birthDate;

    @TableField("gender")
    private Integer gender;

    @TableField("id_number")
    private String idNumber;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("status")
    private Integer status;

    @Override
    public Serializable pkVal() {
        return this.employeeId;
    }
}

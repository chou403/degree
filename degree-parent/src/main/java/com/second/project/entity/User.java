package com.second.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/7/15
 * {@code @description} user
 */
@Data
@TableName(value = "user")
public class User {
    @TableField("user_id")
    private int userId;
    @TableField("user_code")
    private String userCode;
    private String password;
}
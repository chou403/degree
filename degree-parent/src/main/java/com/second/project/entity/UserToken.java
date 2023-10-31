package com.second.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/7/14
 * {@code @description} QrLoginToken
 */
@Data
@TableName(value = "user_token")
public class UserToken {
    private String uuid;
    @TableField("user_id")
    private int userId;
    @TableField("login_time")
    private Date loginTime;
    @TableField("create_time")
    private Date createTime;
    private int state;
    private String token;
}

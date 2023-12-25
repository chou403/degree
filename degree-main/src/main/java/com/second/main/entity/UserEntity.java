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
 * @author chouchou
 * @since 2023-12-06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
@Schema(name = "UserEntity", description = "用户表")
public class UserEntity extends Model<UserEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "账号")
    @TableField("user_code")
    private String userCode;

    @Schema(description = "用户名")
    @TableField("user_name")
    private String userName;

    @Schema(description = "昵称")
    @TableField("nick_name")
    private String nickName;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "年龄")
    @TableField("age")
    private Integer age;

    @Schema(description = "性别")
    @TableField("gender")
    private Integer gender;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}

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
 * <p>
 * 用户表
 * </p>
 *
 * @author chouchou
 * @since 2023-12-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "SysUserEntity", description = "用户表")
public class SysUserEntity extends Model<SysUserEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    @TableField("user_name")
    private String userName;

    @Schema(description = "昵称")
    @TableField("nick_name")
    private String nickName;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "姓名")
    @TableField("name")
    private String name;

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

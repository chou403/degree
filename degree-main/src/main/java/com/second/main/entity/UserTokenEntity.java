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
@TableName("user_token")
@Schema(name = "UserTokenEntity", description = "token表")
public class UserTokenEntity extends Model<UserTokenEntity> {

    @TableId(value = "uuid", type = IdType.AUTO)
    private String uuid;

    @Schema(description = "登录人 id")
    @TableField("user_id")
    private Integer userId;

    @Schema(description = "登录时间")
    @TableField("login_time")
    private Date loginTime;

    @Schema(description = "创建时间 用来判断过期")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "是否二维码失效 0有效 1失效")
    @TableField("state")
    private Integer state;

    @Schema(description = "token")
    @TableField("token")
    private String token;

    @Override
    public Serializable pkVal() {
        return this.uuid;
    }
}

package com.second.common.bean;

import com.second.common.aop.annotations.CurrentBy;
import com.second.common.aop.annotations.CurrentTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.mapping.SqlCommandType;

import java.io.Serializable;
import java.util.Date;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/29
 * {@code @description} 数据库模型设计抽出所有通用字段，抽象为父类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @CurrentTime
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @CurrentTime(SqlCommandType.UPDATE)
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @CurrentBy
    private String creator;

    @CurrentBy(SqlCommandType.UPDATE)
    private String updater;

    private Boolean deleted;
}

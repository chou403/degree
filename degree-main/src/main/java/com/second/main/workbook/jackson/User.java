package com.second.main.workbook.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * {@code @author} chou401
 * {@code @date} 2023/4/7
 * {@code @className} User
 * {@code @description} 1
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 4065532800520420491L;
    private Integer userId;
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
}

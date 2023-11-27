package com.second.main.workbook.blog.domain;

import lombok.Data;

/**
 * 博客文档实体
 */
@Data
public class BlogPaper {
    private String id;

    private String author;

    private String title;

    private String labels;

    private String description;

    private String content;

    private long createDate;

}

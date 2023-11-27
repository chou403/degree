package com.second.main.workbook.blog.domain.mapper;

import com.google.common.base.Joiner;
import com.second.main.workbook.blog.domain.BlogPaper;
import com.second.main.workbook.blog.domain.dto.BlogPaperDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 实体转换类
 */
public final class BlogPaperMapper {

    /**
     * 静态类方法,不支持new对象
     */
    private BlogPaperMapper() {
    }

    /**
     * BlogPaperDTO 转化为 BlogPaper
     *
     * @param dto 转换前对象
     * @return 转换后对象
     */
    public static BlogPaper toEntity(BlogPaperDTO dto) {
        if (dto == null) {
            return null;
        }
        BlogPaper blogPaper = new BlogPaper();
        blogPaper.setId(dto.getId());
        blogPaper.setTitle(dto.getTitle());
        blogPaper.setDescription(dto.getDescription());
        List<String> labels = dto.getLabels();
        if (labels != null && !labels.isEmpty()) {
            blogPaper.setLabels(Joiner.on(",").join(labels));
        }
        blogPaper.setContent(dto.getContent());
        blogPaper.setAuthor(dto.getAuthor());
        if (dto.getCreateDate() != null) {
            blogPaper.setCreateDate(dto.getCreateDate().getTime());
        }
        return blogPaper;
    }

    /**
     * BlogPaper 转化为 BlogPaperDTO
     *
     * @param blogPaper 转换前对象
     * @return 转换后对象
     */
    public static BlogPaperDTO toDTO(BlogPaper blogPaper) {
        if (blogPaper == null) {
            return null;
        }

        BlogPaperDTO dto = new BlogPaperDTO();
        dto.setId(blogPaper.getId());
        dto.setTitle(blogPaper.getTitle());
        dto.setDescription(blogPaper.getDescription());
        dto.setContent(blogPaper.getContent());
        dto.setAuthor(blogPaper.getAuthor());
        if (blogPaper.getCreateDate() != 0) {
            dto.setCreateDate(new Date(blogPaper.getCreateDate()));
        }
        if (StringUtils.isNotBlank(blogPaper.getLabels())) {
            dto.setLabels(Arrays.asList(blogPaper.getLabels().split(",")));
        }
        return dto;
    }
}

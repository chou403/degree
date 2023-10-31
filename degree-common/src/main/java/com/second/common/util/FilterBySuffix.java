package com.second.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.FilenameFilter;

/**
 * {@code @author} chouchou
 * {@code @date} 2022/11/17
 * {@code @className} FilterBySuffix
 * {@code @description} 过滤文件后缀
 */
@Data
@AllArgsConstructor
public class FilterBySuffix implements FilenameFilter {

    private String suffix;

    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param dir  the directory in which the file was found.
     * @param name the name of the file.
     * @return <code>true</code> if and only if the name should be
     * included in the file list; <code>false</code> otherwise.
     */
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }
}

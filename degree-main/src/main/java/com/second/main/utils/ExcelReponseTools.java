package com.second.main.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/7
 * {@code @description}
 */
public class ExcelReponseTools {

    //设置导出样式
    public static void setExcelResponseProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
    }
}

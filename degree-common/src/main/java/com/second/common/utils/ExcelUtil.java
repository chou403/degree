package com.second.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.context.SheetWriteHandlerContext;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.second.common.aop.annotations.ExplicitConstraint;
import com.second.common.excel.BaseEasyExcel;
import com.second.common.excel.ExplicitInterface;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/27
 * {@code @description}
 */
public class ExcelUtil {

    /**
     * 手写校验表头
     *
     * @param is    excel文件流
     * @param clazz 模板类
     */
    public static void checkExcelHeaders(InputStream is, Class clazz) {
        List<String> inputList = new ArrayList<>();
        List<String> requiredList = new ArrayList<>();
        Field[] allFields = clazz.getDeclaredFields();
        for (int i = 0; i < allFields.length; ++i) {
            Field field = allFields[i];
            ExcelProperty attr = field.getAnnotation(ExcelProperty.class);
            if (null != attr) {
                //excel里面加入多余的字段
                requiredList.add(attr.value()[0]);
            }
        }
        try {
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(0);
            for (Iterator<Cell> iter = row.cellIterator(); iter.hasNext(); ) {
                Cell cell = iter.next();
                inputList.add(cell.getStringCellValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (requiredList.stream().anyMatch(e -> !inputList.contains(e))
                || inputList.stream().anyMatch(e -> !requiredList.contains(e))) {
            throw new RuntimeException("Excel表头不一致");
        }
    }

    /**
     * 设置返回的头部信息
     *
     * @param response 返回流信息
     * @param fileName 文件名称
     */
    public static void setResponse(HttpServletResponse response, String fileName) {
        //设置头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String excelFileName = null;
        try {
            excelFileName = URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)).replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        /**
         * attachment这个代表要下载的，如果去掉就直接打开了(attachment-作为附件下载,inline-在线打开)
         * excelFileName是文件名，另存为或者下载时，为默认的文件名
         */
        response.setHeader("Content-disposition", "attachment;filename=" + excelFileName + ".xlsx");
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
    }

    /**
     * 根据模版导出 excel 第一个 sheet 页，多个 sheet 重复操作
     */
    public static void excelWriter(HttpServletResponse response, InputStream inputStream, List<? extends BaseEasyExcel> dataList, Class clazz) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();

            Map<Integer, String[]> explicitListConstraintMap = new HashMap<>();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                //解析注解信息
                ExplicitConstraint explicitConstraint = field.getAnnotation(ExplicitConstraint.class);
                String[] explicitArray = resolveExplicitConstraint(explicitConstraint);
                if (explicitArray != null && explicitArray.length > 0) {
                    explicitListConstraintMap.put(i, explicitArray);
                }
            }

            ExcelWriter excelWriter = EasyExcel.write(outputStream)
                    .registerWriteHandler(new SheetWriteHandler() {
                        public void afterSheetCreate(SheetWriteHandlerContext context) {
                            for (int i = 0; i < dataList.size(); i++) {
                                for (int k : explicitListConstraintMap.keySet()) {
                                    CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(i + 1, i + 1, k, k);
                                    DataValidationHelper helper = context.getWriteSheetHolder().getSheet().getDataValidationHelper();
                                    DataValidationConstraint constraint = helper.createExplicitListConstraint(explicitListConstraintMap.get(k));
                                    DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
                                    context.getWriteSheetHolder().getSheet().addValidationData(dataValidation);
                                }
                            }
                        }
                    })
                    .withTemplate(inputStream)
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();
            WriteSheet oneSheet = EasyExcel.writerSheet(0).build();
            excelWriter.fill(dataList, fillConfig, oneSheet);
            excelWriter.finish();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析注解内容 获取下列表信息
     *
     * @param explicitConstraint
     * @return
     */
    public static String[] resolveExplicitConstraint(ExplicitConstraint explicitConstraint) {
        if (explicitConstraint == null) {
            return null;
        }
        //固定下拉信息
        String[] source = explicitConstraint.source();
        if (source.length > 0) {
            return source;
        }
        //动态下拉信息
        Class<? extends ExplicitInterface>[] classes = explicitConstraint.sourceClass();
        if (classes.length > 0) {
            try {
                ExplicitInterface explicitInterface = classes[0].getDeclaredConstructor().newInstance();
                String[] source1 = explicitInterface.source();
                if (source1.length > 0) {
                    return source1;
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}

package com.second.main.utils;

import com.second.common.aop.annotations.ExcelValid;
import com.second.common.aop.annotations.NumberValid;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/7
 * {@code @description}
 */
public class ExcelImportValid {

    /**
     * Excel导入字段校验
     * @param object 校验的JavaBean 其属性须有自定义注解
     */
    public static void valid(Object object,Integer rowIndex) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            //设置可访问
            field.setAccessible(true);
            //属性的值
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                throw new Exception("第" + rowIndex + "行" + field.getAnnotation(ExcelValid.class).message(),e);
            }
            //是否包含必填校验注解
            boolean isExcelValid = field.isAnnotationPresent(ExcelValid.class);
            if (isExcelValid && Objects.isNull(fieldValue)) {
                throw new Exception("excel中第" + rowIndex + "行的" + field.getAnnotation(ExcelValid.class).message());
            }
        }
    }

    public static void validNumber(Object object,Integer rowIndex) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            //设置可访问
            field.setAccessible(true);
            //属性的值
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                throw new Exception("第" + rowIndex + "行" + field.getAnnotation(NumberValid.class).message(),e);
            }
            //是否包含必填校验注解
            boolean isExcelValid = field.isAnnotationPresent(NumberValid.class);
            if (isExcelValid && (Objects.isNull(fieldValue) || StringUtils.isBlank(fieldValue.toString()))) {
                throw new Exception("excel中第" + rowIndex + "行的年龄不得为空");
            }else if(isExcelValid && !(fieldValue instanceof Integer)){
                throw new Exception("excel中第" + rowIndex + "行的年龄必须为正整数");
            }else if (isExcelValid && (Integer)fieldValue<=0) {
                throw new Exception("excel中第" + rowIndex + "行的" + field.getAnnotation(NumberValid.class).message());
            }
        }
    }
}

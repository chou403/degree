package com.second.main.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

/**
 * Excel 性别转换器
 *
 * {@code @author} william@StarImmortal
 */
public class GenderConverter implements Converter<Integer> {
    @Override
    public Class<?> supportJavaTypeKey() {
        // 实体类中对象属性类型
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        // Excel中对应的CellData(单元格数据)属性类型
        return CellDataTypeEnum.STRING;
    }

    /**
     * 将单元格里的数据转为java对象，也就是女转成2，男转成1，用于导入excel时对性别字段进行转换
     */
    @Override
    public Integer convertToJavaData(ReadConverterContext<?> context) {
        // 从CellData中读取数据，判断Excel中的值，将其转换为预期的数值
        return GenderEnum.convert(context.getReadCellData().getStringValue()).getValue();
    }

    /**
     * 将java对象转为单元格数据，也就是2转成女，1转成男，用于导出excel时对性别字段进行转换
     */
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) {
        // 判断实体类中获取的值，转换为Excel预期的值，并封装为CellData对象
        return new WriteCellData<>(GenderEnum.convert(context.getValue()).getDescription());
    }
}

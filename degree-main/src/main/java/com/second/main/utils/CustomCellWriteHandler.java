package com.second.main.utils;

import com.alibaba.excel.util.BooleanUtils;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import org.apache.poi.ss.usermodel.*;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/7
 * {@code @description}
 */
public class CustomCellWriteHandler implements CellWriteHandler {

    /**
     * 生成的Excel表格的第9列
     */
    private static final Integer COLUMN_INDEX = 1;
    /**
     * 有效期的区间数字_60
     */
    private static final Integer NUMBER_60 = 60;
    /**
     * 有效期的区间数字_30
     */
    private static final Integer NUMBER_30 = 30;
    /**
     * 有效期的区间数字_0
     */
    private static final Integer NUMBER_0 = 0;

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        if (BooleanUtils.isNotTrue(context.getHead())) {
            Cell cell = context.getCell();
            Workbook workbook = context.getWriteWorkbookHolder().getWorkbook();
            // 这里千万记住 想办法能复用的地方把他缓存起来 一个表格最多创建6W个样式
            // 不同单元格尽量传同一个 cellStyle
            CellStyle cellStyle = workbook.createCellStyle();
            if (cell.getColumnIndex() == COLUMN_INDEX) {
                Double doubleCellValue = (Double) cell.getNumericCellValue();
                Integer count = doubleCellValue.intValue();
                //下方设置字体颜色
                Font writeFont = workbook.createFont();
                if (count > NUMBER_60) {
                    writeFont.setColor(IndexedColors.GREEN.getIndex());
                } else if (count >= NUMBER_30) {
                    writeFont.setColor(IndexedColors.YELLOW.getIndex());
                } else if (count >= NUMBER_0) {
                    writeFont.setColor(IndexedColors.RED.getIndex());
                } else {
                    writeFont.setColor(IndexedColors.GREY_25_PERCENT.getIndex());
                }
                cellStyle.setFont(writeFont);

//                //设置单元格颜色
//                if (count > NUMBER_60){
//                    cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//                }else if (count >= NUMBER_30){
//                    cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//                }else if (count >= NUMBER_0){
//                    cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
//                }

//                cellStyle.setBorderLeft(BorderStyle.MEDIUM);
//                cellStyle.setBorderRight(BorderStyle.MEDIUM);
//                cellStyle.setBorderBottom(BorderStyle.THIN);
//                cellStyle.setBorderTop(BorderStyle.THIN);


                // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND
//                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cell.setCellStyle(cellStyle);
                // 由于这里没有指定dataformat 最后展示的数据 格式可能会不太正确
                // 这里要把 WriteCellData的样式清空， 不然后面还有一个拦截器 FillStyleCellWriteHandler 默认会将 WriteCellStyle 设置到
                // cell里面去 会导致自己设置的不一样
                context.getFirstCellData().setWriteCellStyle(null);

            }
        }
    }
}

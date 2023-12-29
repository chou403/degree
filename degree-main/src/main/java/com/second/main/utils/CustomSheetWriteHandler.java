package com.second.main.utils;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/7
 * {@code @description}
 */
public class CustomSheetWriteHandler implements SheetWriteHandler {

    /**
     * 想实现Excel引用其他sheet页数据作为单元格下拉选项值，
     * 需要重写该方法
     *
     * @param writeWorkbookHolder
     * @param writeSheetHolder
     */
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        // 构造样例数据，该数据可根据实际需要，换成业务数据
        // 实际数据可通过构造方法，get、set方法等由外界传入
        List<String> selectDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            selectDataList.add("下拉选项" + i);
        }

        // 构造下拉选项单元格列的位置，以及下拉选项可选参数值的map集合
        // key：下拉选项要放到哪个单元格，比如A列的单元格那就是0，C列的单元格，那就是2
        // value：key对应的那个单元格下拉列表里的数据项，比如这里就是下拉选项1..100
        Map<Integer, List<String>> selectParamMap = new HashMap<>();
        selectParamMap.put(0, selectDataList);

        // 获取第一个sheet页
        Sheet sheet = writeSheetHolder.getCachedSheet();
        // 获取sheet页的数据校验对象
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 获取工作簿对象，用于创建存放下拉数据的字典sheet数据页
        Workbook workbook = writeWorkbookHolder.getWorkbook();

        // 迭代索引，用于存放下拉数据的字典sheet数据页命名
        int index = 1;
        for (Map.Entry<Integer, List<String>> entry : selectParamMap.entrySet()) {

            // 设置存放下拉数据的字典sheet，并把这些sheet隐藏掉，这样用户交互更友好
            String dictSheetName = "dict_hide_sheet" + index;
            Sheet dictSheet = workbook.createSheet(dictSheetName);
            // 隐藏字典sheet页
            workbook.setSheetHidden(index++, true);

            // 设置下拉列表覆盖的行数，从第一行开始到最后一行，这里注意，Excel行的
            // 索引是从0开始的，我这边第0行是标题行，第1行开始时数据化，可根据实
            // 际业务设置真正的数据开始行，如果要设置到最后一行，那么一定注意，
            // 最后一行的行索引是1048575，千万别写成1048576，不然会导致下拉列表
            // 失效，出不来
            CellRangeAddressList infoList = new CellRangeAddressList(1, 1048575, entry.getKey(), entry.getKey());
            int rowLen = entry.getValue().size();
            for (int i = 0; i < rowLen; i++) {
                // 向字典sheet写数据，从第一行开始写，此处可根据自己业务需要，自定
                // 义从第几行还是写，写的时候注意一下行索引是从0开始的即可
                dictSheet.createRow(i).createCell(0).setCellValue(entry.getValue().get(i));
            }

            // 设置关联数据公式，这个格式跟Excel设置有效性数据的表达式是一样的
            String refers = dictSheetName + "!$A$1:$A$" + entry.getValue().size();
            Name name = workbook.createName();
            name.setNameName(dictSheetName);
            // 将关联公式和sheet页做关联
            name.setRefersToFormula(refers);

            // 将上面设置好的下拉列表字典sheet页和目标sheet关联起来
            DataValidationConstraint constraint = helper.createFormulaListConstraint(dictSheetName);
            DataValidation dataValidation = helper.createValidation(constraint, infoList);
            sheet.addValidationData(dataValidation);
        }
    }
}

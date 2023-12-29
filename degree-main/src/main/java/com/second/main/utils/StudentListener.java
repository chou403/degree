package com.second.main.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.second.main.entity.StudentExcel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/7
 * {@code @description}
 */
@Slf4j
public class StudentListener extends AnalysisEventListener<StudentExcel> {

    private static final int BATCH_COUNT = 500;

    @Getter
    List<StudentExcel> list=new ArrayList<>(BATCH_COUNT);

    @Override
    @SneakyThrows
    public void invoke(StudentExcel studentExcel, AnalysisContext analysisContext) {

        log.info("数据校验:"+studentExcel);
        ExcelImportValid.valid(studentExcel,analysisContext.readRowHolder().getRowIndex()+1);
        ExcelImportValid.validNumber(studentExcel,analysisContext.readRowHolder().getRowIndex()+1);
        //可不用
//        list.add(studentExcel);
//        if (list.size() >= BATCH_COUNT) {
//            log.info("已经解析"+list.size()+"条数据");
//            list.clear();
//        }
    }

    /**
     * 每解析一行表头，会调用该方法
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (!headMap.containsKey(0) || !headMap.containsKey(1) || !headMap.containsKey(2) || !headMap.containsKey(3)
                || !headMap.get(0).equals("姓名") || !headMap.get(1).equals("年龄")
                || !headMap.get(2).equals("性别") || !headMap.get(3).equals("日期")) {
            throw new RuntimeException("表头校验失败");
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

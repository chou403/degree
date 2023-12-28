package com.second.main.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.second.common.bean.reponse.Result;
import com.second.common.utils.ExcelUtil;
import com.second.main.entity.ExcelTestDTO;
import com.second.main.entity.StudentExcel;
import com.second.main.entity.UserEntity;
import com.second.main.service.UserService;
import com.second.main.utils.CustomSheetWriteHandler;
import com.second.main.utils.ExcelReponseTools;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel导入导出
 *
 * @author william@StarImmortal
 */
@Tag(name = "easy excel")
@RestController
@RequestMapping("/excel")
public class ExcelController {

    private final UserService userService;

    public ExcelController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "导入")
    @PostMapping(value = "/import/user")
    public Result<Object> importUserExcel(@RequestParam(value = "file") MultipartFile file) {
        try {
            List<UserEntity> userList = EasyExcel.read(file.getInputStream())
                    .head(StudentExcel.class)
                    .sheet()
                    .doReadSync();
            return Result.success(userList);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
    }

    @Operation(summary = "导出")
    @GetMapping("/export/user")
    public void exportUserExcel(HttpServletResponse response) {
        try {
            ExcelReponseTools.setExcelResponseProp(response, "用户列表");
            List<UserEntity> userList = userService.list();

            List<StudentExcel> excelList = new ArrayList<>();

            userList.forEach(v -> {
                StudentExcel studentExcel = new StudentExcel();
                BeanUtils.copyProperties(v, studentExcel);
                excelList.add(studentExcel);
            });

            EasyExcel.write(response.getOutputStream())
                    .registerWriteHandler(new CustomSheetWriteHandler())
                    .head(StudentExcel.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("用户列表")
                    .doWrite(excelList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "根据模版导出列表数据 （带下拉操作）")
    @GetMapping("/templateExport")
    public void export(HttpServletRequest request,
                       HttpServletResponse response) {

        try {

            InputStream inputStream = ResourceUtil.getResourceObj("templates/userExport.xlsx").getStream();

            List<ExcelTestDTO> ExcelTestDTOList = new ArrayList<>();
            initializationExcelTestDTO(ExcelTestDTOList);

            ExcelUtil.setResponse(response, "人员信息");
            ExcelUtil.excelWriter(response, inputStream, ExcelTestDTOList, ExcelTestDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void initializationExcelTestDTO(List<ExcelTestDTO> ExcelTestDTOs) {
        ExcelTestDTO t = new ExcelTestDTO();
        t.setName("张三");
        t.setCode("code123");
        t.setGender("男");
        t.setAge("24");
        ExcelTestDTO t1 = new ExcelTestDTO();
        t1.setName("李四");
        t1.setCode("code456");
        t1.setGender("女");
        t1.setAge("30");
        ExcelTestDTOs.add(t);
        ExcelTestDTOs.add(t1);
    }

}

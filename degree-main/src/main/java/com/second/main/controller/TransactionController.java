package com.second.main.controller;

import com.second.common.aop.advice.BizException;
import com.second.common.bean.reponse.Result;
import com.second.main.entity.EmployeeEntity;
import com.second.main.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} transaction controller
 */
@Tag(name = "多线程 事务异常 回滚")
@Slf4j
@RestController
@RequestMapping("/theadTransaction")
public class TransactionController {

    private final EmployeeService employeeService;

    public TransactionController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "编程式事务")
    @GetMapping("/programTransaction")
    public Result<Object> programTransaction() {

        int size = 10;
        List<EmployeeEntity> employeeDOList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            EmployeeEntity employee = new EmployeeEntity();
            employee.setEmployeeName("lol" + i);
            employee.setAge(18);
            employee.setGender(1);
            employee.setBirthDate(Calendar.getInstance().getTime());
            employee.setIdNumber(i + "XX");
            employee.setStatus(1);
            employee.setCreateTime(Calendar.getInstance().getTime());
            employee.setUpdateTime(Calendar.getInstance().getTime());
            employeeDOList.add(employee);
        }

        try {
            employeeService.saveProgramTransaction(employeeDOList);
        } catch (Exception e) {
            log.error("添加失败:{}", e.getMessage());
            throw new BizException(e.getMessage());
        }

        return Result.success();
    }

    @Operation(summary = "声明式事务")
    @GetMapping("/declarativeTransaction")
    public Result<Object> declarativeTransaction() {

        try {
            employeeService.saveDeclarativeTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
//            throw new BizException(e.getMessage());
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

}

package com.second.main.controller;

import com.second.common.aop.advice.BizException;
import com.second.main.entity.Employee;
import com.second.main.service.EmployeeService;
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
 * {@code @description} employee controller
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String batchAddEmployee() {

        int size = 10;
        List<Employee> employeeDOList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Employee employee = new Employee();
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
            employeeService.saveThread(employeeDOList);
            System.out.println("添加成功");
        } catch (Exception e) {
            log.error("添加失败:{}", e.getMessage());
            throw new BizException("添加失败");
        }

        return "添加成功";
    }

}

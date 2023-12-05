package com.second.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.second.main.entity.Employee;

import java.util.List;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} EmployeeService
 */
public interface EmployeeService extends IService<Employee> {

    void saveThread(List<Employee> employeeList) throws Exception;

}

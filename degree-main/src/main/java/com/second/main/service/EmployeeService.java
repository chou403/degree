package com.second.main.service;

import com.second.main.entity.Employee;

import java.util.List;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} EmployeeService
 */
public interface EmployeeService {

    void saveThread(List<Employee> employeeList) throws Exception;

}

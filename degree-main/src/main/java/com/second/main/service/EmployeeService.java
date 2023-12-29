package com.second.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.second.main.entity.EmployeeEntity;

import java.util.List;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/1
 * {@code @description} EmployeeService
 */
public interface EmployeeService extends IService<EmployeeEntity> {

    void saveProgramTransaction(List<EmployeeEntity> employeeList) throws Exception;

    void saveDeclarativeTransaction();

}

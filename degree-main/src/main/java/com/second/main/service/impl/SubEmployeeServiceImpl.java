package com.second.main.service.impl;

import com.second.common.aop.advice.BizException;
import com.second.common.aop.annotations.SonTransaction;
import com.second.main.entity.EmployeeEntity;
import com.second.main.mapper.EmployeeMapper;
import com.second.main.service.SubEmployeeService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/6
 * {@code @description}
 */
@Service
public class SubEmployeeServiceImpl implements SubEmployeeService {

    private final EmployeeMapper employeeMapper;

    public SubEmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 参数说明:  以下4个方法参数和此相同
     *
     * @param args   业务中需要传递的参数
     * @param thread 调用者的线程, 用于aop获取参数, 不建议以方法重写的方式简略此参数,
     *               在调用者方法中可以以此参数为标识计算子线程的个数作为注解参数,避免线程参数计算错误导致锁表
     *               传参时参数固定为: Thread.currentThread()
     */
    @Transactional(rollbackFor = Exception.class)
    @Async("threadPoolTaskExecutor")
    @SonTransaction
    @Override
    public void sonMethod1(String args, Thread thread) {
        saveEmployeeInfo(args);
    }

    @Transactional(rollbackFor = Exception.class)
    @Async("threadPoolTaskExecutor")
    @SonTransaction
    @Override
    public void sonMethod2(String args1, String args2, Thread thread) {
        saveEmployeeInfo(args1 + args2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Async("threadPoolTaskExecutor")
    @SonTransaction
    @Override
    public void sonMethod3(String args, Thread thread) {
        saveEmployeeInfo(args);
        throw new BizException("sonMethod3 异常...");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sonMethod4(String args) {
        saveEmployeeInfo(args);
    }

    public void saveEmployeeInfo(String args) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeName(args);
        employee.setAge(18);
        employee.setGender(1);
        employee.setBirthDate(Calendar.getInstance().getTime());
        employee.setIdNumber(String.valueOf(Math.random()));
        employee.setStatus(1);
        employee.setCreateTime(Calendar.getInstance().getTime());
        employee.setUpdateTime(Calendar.getInstance().getTime());
        employeeMapper.insert(employee);
    }
}

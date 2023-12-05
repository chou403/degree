package com.second.main.controller;

import com.second.common.bean.reponse.Result;
import com.second.main.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} 注解解决多事务处理
 */
@Tag(name = "多线程声明式事务")
@Slf4j
@RestController
@RequestMapping("/transactionTest")
public class TransactionController {

    @Autowired
    MainService mainService;

    @Operation(summary = "测试")
    @GetMapping("/get")
    public Result get() throws Exception {
        mainService.test1();
        return Result.success();
    }

    @GetMapping("/getError")
    public void getError() {
        mainService.getError();
    }

}

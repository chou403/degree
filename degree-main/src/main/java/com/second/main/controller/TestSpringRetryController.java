package com.second.main.controller;

import com.second.common.bean.reponse.Result;
import com.second.main.service.TestSpringRetryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/14
 * {@code @description} 测试 spring retry 重试
 */
@Tag(name = "spring retry")
@Slf4j
@RestController
@RequestMapping("/testSpringRetry")
public class TestSpringRetryController {

    private final TestSpringRetryService testSpringRetryService;

    public TestSpringRetryController(TestSpringRetryService testSpringRetryService) {
        this.testSpringRetryService = testSpringRetryService;
    }

    @Operation(summary = "重试")
    @GetMapping("/getRetryNum")
    public Result<Integer> getRetryNum(@RequestParam int num) {
        return testSpringRetryService.getRetryNum(num);
    }
}

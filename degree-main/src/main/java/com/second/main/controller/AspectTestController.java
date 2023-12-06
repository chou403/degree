package com.second.main.controller;

import com.second.common.aop.annotations.NotResubmit;
import com.second.common.aop.annotations.OnlyIntranetAccess;
import com.second.common.bean.reponse.Result;
import com.second.common.utils.JsonHelper;
import com.second.main.domains.TestValidDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/16
 * {@code @description} test
 */
@Tag(name = "切面 测试")
@Slf4j
@RestController
@RequestMapping("/aspectTest")
public class AspectTestController {

    @Operation(summary = "测试@Valid")
    @PostMapping("/test-valid")
    public Result testValid(@Valid @RequestBody(required = false) TestValidDTO dto) {
        log.info("参数：{}", JsonHelper.parseToJson(dto));
        return Result.success();
    }

    @Operation(summary = "测试@NotResubmit")
    @GetMapping("/testResubmit")
    @NotResubmit(delaySeconds = 3)
    public Result testResubmit() {
        log.info("调用成功");
        return Result.success();
    }

    @Operation(summary = "测试@OnlyIntranetAccess")
    @GetMapping("/testIntranetAccess")
    @OnlyIntranetAccess
    public Result testIntranetAccess() {
        log.info("该接口只允许内部服务调用");
        return Result.success();
    }

}

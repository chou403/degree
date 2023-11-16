package com.second.project.controller;

import com.second.common.aop.annotations.NotResubmit;
import com.second.common.bean.reponse.Result;
import com.second.common.utils.JsonHelper;
import com.second.project.domains.TestValidDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/16
 * {@code @description} test
 */
@Slf4j
@RestController
@RequestMapping("/aspectTest")
public class AspectTestController {

    @PostMapping("/test-valid")
    public Result testValid(@Valid @RequestBody TestValidDTO dto) {
        log.info("参数：{}", JsonHelper.parseToJson(dto));
        return Result.success();
    }


    @GetMapping("/testResubmit")
    @NotResubmit(delaySeconds = 3)
    public Result testResubmit() {
        log.info("调用成功");
        return Result.success();
    }

}

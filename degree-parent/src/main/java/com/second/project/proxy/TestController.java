package com.second.project.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/9/13
 * {@code @description} test controller
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ICustomServiceImpl iCustomService;

    @GetMapping("/getTest")
    public String getTest() {
        System.out.println(iCustomService);
        System.out.println(iCustomService.getClass());
        return "test";
    }


}

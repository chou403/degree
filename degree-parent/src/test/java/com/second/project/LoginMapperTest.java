package com.second.project;

import com.second.project.mapper.UserTokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

@SpringBootTest
@Slf4j
public class LoginMapperTest {

    @Resource
    private UserTokenMapper loginMapper;

    @Test
    public void testSelectList() {
        loginMapper.selectList(null).forEach(System.out::println);
    }
}

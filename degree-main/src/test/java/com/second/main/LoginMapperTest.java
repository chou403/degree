package com.second.main;

import com.second.main.mapper.UserTokenMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginMapperTest {

    @Resource
    private UserTokenMapper userTokenMapper;

    @Test
    public void testSelectList() {
        userTokenMapper.selectList(null).forEach(System.out::println);
    }
}

package com.second.main.service.impl;

import com.second.common.aop.advice.BizException;
import com.second.common.aop.annotations.MainTransaction;
import com.second.main.service.MainService;
import com.second.main.service.SonService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} 测试主service
 */
@Slf4j
@Service
public class MainServiceImpl implements MainService {

    @Resource
    private SonService sonService;

    @MainTransaction(3)//调用的方法中sonMethod1/sonMethod2/sonMethod3使用@Async开启了线程, 所以参数为: 3
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test1() throws Exception {
        sonService.sonMethod1("路飞", Thread.currentThread());
        sonService.sonMethod2("索隆", "山治", Thread.currentThread());
        sonService.sonMethod3("娜美", Thread.currentThread());
        sonService.sonMethod4("罗宾");
    }

    @Override
    public void getError() {
        log.info("get error");
//        int a = 1 / 0;
        throw new BizException("333333");
//        log.error("test get error");
    }
}

package com.second.project.service.impl;

import com.second.common.aop.advice.BizException;
import com.second.common.util.DateUtils;
import com.second.project.service.TestSpringRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/14
 * {@code @description} test spring retry service impl
 */
@Slf4j
@Service
public class TestSpringRetryServiceImpl implements TestSpringRetryService {

    private static final int TOTAL_NUM = 10000;

    @Override
    @Retryable(backoff = @Backoff(delay = 5000L, multiplier = 2))
    public int getRetryNum(int num) {
        log.info("start date : {}", DateUtils.getCurrentDateHms());
        if (num <= 0) {
            throw new BizException("数量不对");
        }
        log.info("end date : {}", DateUtils.getCurrentDateHms());
        return TOTAL_NUM - num;
    }
}

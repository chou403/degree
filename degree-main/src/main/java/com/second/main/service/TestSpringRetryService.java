package com.second.main.service;

import com.second.common.bean.reponse.Result;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/14
 * {@code @description} test spring retry service
 */
public interface TestSpringRetryService {

    Result<Integer> getRetryNum(int num);

}

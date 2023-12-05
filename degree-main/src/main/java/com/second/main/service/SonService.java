package com.second.main.service;

import org.springframework.stereotype.Service;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} 测试子 service
 */
@Service
public interface SonService {

    void sonMethod1(String args, Thread thread) throws Exception;

    void sonMethod2(String args1, String args2, Thread thread) throws Exception;

    void sonMethod3(String args, Thread thread) throws Exception;

    void sonMethod4(String args) throws Exception;
}

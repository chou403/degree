package com.second.main.workbook.proxy;

import org.springframework.stereotype.Service;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/9/12
 * {@code @description} a
 */
@Service
public class ICustomServiceImpl implements ICustomService{
    public void test() {
        System.out.println("custom service impl");
    }
}

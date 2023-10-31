package com.second.project.proxy;

import org.springframework.stereotype.Service;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/9/12
 * {@code @description} a
 */
@Service
public class ICustomServiceImpl implements ICustomService{
    public void test() {
        System.out.println("custom service impl");
    }
}

package com.second.project.other.mybatis;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/27
 * {@code @className} TestImportBeanDefinitionRegistrar
 * {@code @description} bean definition registrar
 */
public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(TestMapperScan.class.getName());
        String path = (String) annotationAttributes.get("value");

        TestMapperScanner testMapperScanner = new TestMapperScanner(registry);
        testMapperScanner.scan(path);
    }
}

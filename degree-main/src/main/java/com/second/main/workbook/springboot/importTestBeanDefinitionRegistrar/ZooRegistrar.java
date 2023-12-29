package com.second.main.workbook.springboot.importTestBeanDefinitionRegistrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/8/23
 * {@code @description} importBeanDefinitionRegistrar
 */
public class ZooRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(Dog.class);
        registry.registerBeanDefinition("dog", bd);
    }
}

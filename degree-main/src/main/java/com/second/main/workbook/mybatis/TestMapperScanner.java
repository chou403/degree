package com.second.main.workbook.mybatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * {@code @author} chou401
 * {@code @date} 2023/3/27
 * {@code @className} TestMapperScanner
 * {@code @description} mapper scanner
 */
public class TestMapperScanner extends ClassPathBeanDefinitionScanner {

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        // 是否 @component 注解
        return true;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        // 是否 是接口类
        return beanDefinition.getMetadata().isInterface();
    }

    public TestMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 重写 doScan 方法
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(genericBeanDefinition.getBeanClassName()));
            genericBeanDefinition.setBeanClass(TestFactoryBean.class);
        }

        return beanDefinitionHolders;
    }
}

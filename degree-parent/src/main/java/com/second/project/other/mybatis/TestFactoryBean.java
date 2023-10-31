package com.second.project.other.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/27
 * {@code @className} TestFactoryBean
 * {@code @description} factory bean
 * 会生成两个Bean对象 1、TestFactoryBean 2、getObject
 * 先生成TestFactoryBean 之后再生成 getObject
 */
//@Component
public class TestFactoryBean implements FactoryBean {

    //    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private final Class mapperClass;

    public TestFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public Object getObject() throws Exception {
        sqlSessionFactory.getConfiguration().addMapper(mapperClass);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.getMapper(mapperClass);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }
}

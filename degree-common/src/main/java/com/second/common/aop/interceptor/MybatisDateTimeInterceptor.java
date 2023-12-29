package com.second.common.aop.interceptor;

import com.second.common.aop.annotations.CurrentBy;
import com.second.common.aop.annotations.CurrentTime;
import com.second.common.bean.BaseEntity;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/29
 * {@code @description} mybatis统一处理entity创建日期和更新日期
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
)})
@Component
public class MybatisDateTimeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        //获取 SQL 命令
        SqlCommandType sqlCommandType = ms.getSqlCommandType();
        //只判断新增和修改
        if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
            //获取参数
            Object parameter = invocation.getArgs()[1];
            //批量操作时
            if (parameter instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap map = (MapperMethod.ParamMap) parameter;
                Object obj = map.get("list");
                List<?> list = (List<?>) obj;
                if (list != null) {
                    for (Object o : list) {
                        setParameter(o, sqlCommandType);
                    }
                }
            } else {
                setParameter(parameter, sqlCommandType);
            }
        }
        return invocation.proceed();
    }

    public void setParameter(Object parameter, SqlCommandType sqlCommandType) throws Throwable {
        Class<?> aClass = parameter.getClass();
        Field[] declaredFields;
        // 判断BaseEntity是否是父类
        if (BaseEntity.class.isAssignableFrom(aClass)) {
            // 获取父类私有成员变量
            declaredFields = aClass.getSuperclass().getDeclaredFields();
        } else {
            // 获取私有成员变量
            declaredFields = aClass.getDeclaredFields();
        }
        for (Field field : declaredFields) {
            if (field.getAnnotation(CurrentTime.class) != null) {
                CurrentTime annotation = field.getAnnotation(CurrentTime.class);
                if (sqlCommandType.equals(annotation.value())) {
                    field.setAccessible(true);
                    field.set(parameter, new Date());
                }
            }
            if (field.getAnnotation(CurrentBy.class) != null) {
                CurrentBy annotation = field.getAnnotation(CurrentBy.class);
                if (sqlCommandType.equals(annotation.value())) {
                    field.setAccessible(true);
                    field.set(parameter, "chou401");
                }
            }
        }
    }
}

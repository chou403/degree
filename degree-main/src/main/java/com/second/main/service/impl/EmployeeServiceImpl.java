package com.second.main.service.impl;

import com.second.common.aop.advice.BizException;
import com.second.common.config.ExecutorConfig;
import com.second.common.config.SqlContext;
import com.second.common.utils.CommonUtil;
import com.second.main.entity.Employee;
import com.second.main.mapper.EmployeeMapper;
import com.second.main.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} EmployeeServiceImpl
 */
@Slf4j
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final SqlContext sqlContext;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper, SqlContext sqlContext) {
        this.sqlContext = sqlContext;
    }

    /**
     * 使用@Transactional
     * 子线程出错，不会回滚主线程
     */
//    @Override
//    @Transactional
//    public void saveThread(List<Employee> employeeList) {
//        try {
//            //先做删除操作,如果子线程出现异常,此操作不会回滚
//            employeeMapper.delete(null);
//            //获取线程池
//            ExecutorService service = ExecutorConfig.getThreadPool();
//            //拆分数据,拆分5份
//            List<List<Employee>> lists = CommonUtil.averageAssign(employeeList, 5);
//            //执行的线程
//            Thread[] threadArray = new Thread[lists.size()];
//            //监控子线程执行完毕,再执行主线程,要不然会导致主线程关闭,子线程也会随着关闭
//            CountDownLatch countDownLatch = new CountDownLatch(lists.size());
//            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
//            for (int i = 0; i < lists.size(); i++) {
//                if (i == lists.size() - 1) {
//                    atomicBoolean.set(false);
//                }
//                List<Employee> list = lists.get(i);
//                threadArray[i] = new Thread(() -> {
//                    try {
//                        //最后一个线程抛出异常
//                        if (!atomicBoolean.get()) {
//                            throw new BizException("出现异常001");
//                        }
//                        //批量添加,mybatisPlus中自带的batch方法
//                        employeeMapper.saveBatchEmployee(list);
//                    } finally {
//                        countDownLatch.countDown();
//                    }
//
//                });
//            }
//            for (int i = 0; i < lists.size(); i++) {
//                service.execute(threadArray[i]);
//            }
//            //当子线程执行完毕时,主线程再往下执行
//            countDownLatch.await();
//            System.out.println("添加完毕");
//        } catch (Exception e) {
//            log.info("error", e);
//            throw new BizException("出现异常002");
//        } finally {
////            connection.close();
//        }
//    }

    /**
     * 使用sqlSession控制手动提交事务
     */
//    @Override
//    public void saveThread(List<Employee> employeeList) throws Exception {
//        // 获取数据库连接,获取会话(内部自有事务)
//        SqlSession sqlSession = sqlContext.getSqlSession();
//        Connection connection = sqlSession.getConnection();
//        try {
//            // 设置手动提交
//            connection.setAutoCommit(false);
//            //获取mapper
//            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
//            //先做删除操作
//            employeeMapper.delete(null);
//            //获取执行器
//            ExecutorService service = ExecutorConfig.getThreadPool();
//            List<Callable<Integer>> callableList = new ArrayList<>();
//            //拆分list
//            List<List<Employee>> lists = CommonUtil.averageAssign(employeeList, 5);
//            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
//            for (int i = 0; i < lists.size(); i++) {
//                if (i == lists.size() - 1) {
//                    atomicBoolean.set(false);
//                }
//                List<Employee> list = lists.get(i);
//                //使用返回结果的callable去执行,
//                Callable<Integer> callable = () -> {
//                    //让最后一个线程抛出异常
//                    if (!atomicBoolean.get()) {
//                        throw new BizException("出现异常001");
//                    }
//                    return employeeMapper.saveBatchEmployee(list);
//                };
//                callableList.add(callable);
//            }
//            //执行子线程
//            List<Future<Integer>> futures = service.invokeAll(callableList);
//            for (Future<Integer> future : futures) {
//                //如果有一个执行不成功,则全部回滚
//                if (future.get() <= 0) {
//                    connection.rollback();
//                    return;
//                }
//            }
//            connection.commit();
//            System.out.println("添加完毕");
//        } catch (Exception e) {
//            connection.rollback();
//            log.info("error", e);
//            throw new BizException("出现异常002");
//        } finally {
//            connection.close();
//        }
//    }

    /**
     * 改造成功处理多线程回滚示例代码
     */
    @Override
    public void saveThread(List<Employee> employeeList) throws Exception {
        // 获取数据库连接,获取会话(内部自有事务)
        SqlSession sqlSession = sqlContext.getSqlSession();
        Connection connection = sqlSession.getConnection();
        try {
            // 设置手动提交
            connection.setAutoCommit(false);
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            //先做删除操作
            employeeMapper.delete(null);
            ExecutorService service = ExecutorConfig.getThreadPool();
            List<Callable<Integer>> callableList = new ArrayList<>();
            List<List<Employee>> lists = CommonUtil.averageAssign(employeeList, 5);
            for (List<Employee> list : lists) {
                Callable<Integer> callable = () -> employeeMapper.saveBatchEmployee(list);
                callableList.add(callable);
            }
            //执行子线程
            List<Future<Integer>> futures = service.invokeAll(callableList);
            for (Future<Integer> future : futures) {
                if (future.get() <= 0) {
                    connection.rollback();
                    return;
                }
            }
            connection.commit();
            System.out.println("添加完毕");
        } catch (Exception e) {
            connection.rollback();
            log.info("error", e);
            throw new BizException("出现异常002");
        } finally {
            connection.close();
        }

    }
}

package com.second.main.workbook.reentrantLock;

import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Seq;

/**
 * park test
 * {@code @author}  chou401
 * {@code @date}    2023/4/11 11:06
 */
@Slf4j
public class ParkTest {

    public static void main(String[] args) {
//        final Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                logger.info("线程1执行");
//                LockSupport.park();
//                logger.info("线程1执行解堵塞");
//            }
//        }, "线程1");
//        thread1.start();
//
//        final Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                logger.info("线程2执行");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                LockSupport.unpark(thread1);
//            }
//        }, "线程2");
//        thread2.start();

//        List<Integer> list = Arrays.asList(1, 2, 3);
//        Seq<Integer> seq = list::forEach;
//        Seq<Integer> seq1 = c->list.forEach(c);

        Seq<Integer> s = Seq.range(1, 5);
        s.filter(n -> n < 3).forEach(System.out::println);


    }
}

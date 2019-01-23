package com.swq.action;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool {

    /**
     * 线程池的基本大小
     */
    static int corePoolSize = 4;
    /**
     * 线程池最大数量
     */
    static int maximumPoolSizeSize = 10000;
    /**
     * 线程活动保持时间
     */
    static long keepAliveTime = 60;
    /**
     * 任务队列
     */
    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);

    public static void main(String[] args) throws Exception {

        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
        long a = System.currentTimeMillis();
        List<Future<String>> list = new ArrayList<>();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                corePoolSize,
//                maximumPoolSizeSize,
//                keepAliveTime,
//                TimeUnit.SECONDS,
//                workQueue,
//                new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build());
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i=0;i<1000;i++){
            //提交一个任务
            SwqThread t1 = new SwqThread("我是第"+i+"个任务");
            //执行任务并获取Future对象
            Future<String> future  = executor.submit(t1);
            list.add(future);
        }
        executor.shutdown();
        while(true){
            if(executor.isTerminated()){
                for (Future<String> f:list){
                    System.out.println(f.get());
                }
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }
        long b = System.currentTimeMillis();
        System.out.println("一共耗时："+(b-a)+"ms");
    }
}
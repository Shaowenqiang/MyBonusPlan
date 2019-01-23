package com.swq.action;

import com.swq.service.UserService;
import com.swq.util.ApplicationContextHolder;
import com.swq.util.FrameSpringBeanUtil;
import com.swq.util.SpringApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ${todo}(这里一句话描述这个类的作用)
 *
 * @author ShaoWenQiang
 * @date 2019/1/14 0014 下午 2:54
 */
class SwqThread implements Callable<String> {
    private String bidName;
    @Autowired
    private ApplicationContextHolder holder;
    public SwqThread(String bidName) {
        this.bidName = bidName;
    }

    @Override
    public String call() throws Exception {
        Thread curThread = Thread.currentThread();
        UserService user = FrameSpringBeanUtil.getBean(UserService.class);
        user.createNum();
        System.out.println("正在使用的线程名称："+curThread.getName());
        System.out.println("参数bidName=" + this.bidName);
        return "我是线程:" + curThread.getName() + "返回值,参数bidName=" + this.bidName;
    }
}

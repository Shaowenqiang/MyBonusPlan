package com.swq.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ${todo}(这里一句话描述这个类的作用)
 *
 * @author ShaoWenQiang
 * @date 2019/1/15 0015 下午 1:03
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext=applicationContext;
        System.out.println("applicationContext---->"+applicationContext);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name) {

        if (applicationContext==null) {
            System.out.println("applicationContext为空");
        }
        return (T) applicationContext.getBean(name);

    }
}
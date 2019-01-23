package com.swq.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import static org.apache.commons.lang3.Validate.notEmpty;

/**
 * SpringApplicationContextHolder 工具类
 *
 * @author ShaoWenQiang
 * @date 2019/1/15 0015 下午 12:35
 */
@Configuration
public class SpringApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public  static <T> T getBean(Class<T> aClass){
        return context.getBean(aClass);
    }
}

package com.swq.util;

import org.springframework.stereotype.Component;


@Component
public class MyTask {
    public void execute() {
        System.out.println("基于spring+quartz实现定时任务！");
    }
}
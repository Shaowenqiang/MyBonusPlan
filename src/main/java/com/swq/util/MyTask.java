package com.swq.util;

import com.swq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MyTask {
    @Autowired
    private UserService service;
    public void execute() {
        String str = "";
//        str = service.createMoreNum();
        System.out.println("基于spring+quartz实现定时任务！"+str);
    }
}
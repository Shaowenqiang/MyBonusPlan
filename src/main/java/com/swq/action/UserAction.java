package com.swq.action;

import com.swq.service.UserService;
import com.swq.util.ListOperationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Controller
@RequestMapping("/user")
public class UserAction {
    Logger logger = LogManager.getLogger(UserAction.class);
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping("/createNum")
    public String createNum() throws Exception {
        long a = System.currentTimeMillis();
        List<Future<List<Map>>> list = new ArrayList();
        ExecutorService executor = newFixedThreadPool(8);
        for (int i=0;i<8;i++){
            //提交一个任务
            BonusThread t1 = new BonusThread("我是第"+i+"个任务");
            //执行任务并获取Future对象
            Future<List<Map>> future  = executor.submit(t1);
            list.add(future);
        }
        executor.shutdown();
        List<Map> resultList=new ArrayList();
        while(true){
            if(executor.isTerminated()){
                for (Future<List<Map>> f:list){
                    resultList.addAll(f.get());
                }
                System.out.println("所有的子线程都结束了！");
                executor.shutdownNow();
                break;
            }
        }
        List<List<Map>> splitList = ListOperationUtil.splitList(resultList,10000);
        for (List<Map> item : splitList) {
            service.insertBatch(item);
        }
        long b = System.currentTimeMillis();
        System.out.println("success");
        System.out.println("耗时："+(b-a)/1000+"s");
        return "success";
    }
}

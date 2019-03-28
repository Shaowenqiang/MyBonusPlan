package com.swq.action;

import com.swq.service.UserService;
import com.swq.util.FrameSpringBeanUtil;
import com.swq.util.ListOperationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.Executors.unconfigurableExecutorService;

@Controller
@RequestMapping("/user")
public class UserAction {
    Logger logger = LogManager.getLogger(UserAction.class);
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping("/createNum")
    public String createNum() throws Exception {
        return service.createMoreNum();
    }
    @ResponseBody
    @RequestMapping("/getNovelNames")
    public void getNovelNames(String[] args) throws Exception {
        long a = System.currentTimeMillis();
        List<Future<List>> list = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 1;i<12;i++){
            String[] cate = {
                    "xuanhuan",
                    "yanqing",
                    "wuxia",
                    "danmei",
                    "xiaoyuan",
                    "kehuan",
                    "chuanyeu",
                    "wangyou",
                    "lishi",
                    "yanqing",
                    "wenxue"
            };
            for(int j = 1;j<680;j++){
                //提交一个任务
                String url = "http://www.qishu.cc/"+cate[i-1]+"/list"+i+"_"+j+".html";
                SwqDownloadNovel t1 = new SwqDownloadNovel(url,cate[i-1]);
                //执行任务并获取Future对象
                Future<List> future  = executor.submit(t1);
                list.add(future);
            }
        }
        executor.shutdown();
        while(true){
            if(executor.isTerminated()){
                for (Future<List> f:list){
                    service.insertBatchForNovel(f.get());
                }
                break;
            }
        }
        long b = System.currentTimeMillis();
        System.out.println("一共耗时："+(b-a)+"ms");
    }

    @ResponseBody
    @RequestMapping("/getNovelNames_2")
    public void getNovelNames_2(String[] args) throws Exception {
        long a = System.currentTimeMillis();
        List<Future<List>> list = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(8);
        String[] cate = {
                "xuanhuan",
                "yanqing",
                "wuxia",
                "danmei",
                "xiaoyuan",
                "kehuan",
                "chuanyeu",
                "wangyou",
                "lishi",
                "yanqing",
                "wenxue"
        };
        int i = 6;
        int j_num  = 200;
        for(int j = 1;j<j_num;j++){
                //提交一个任务
                String url = "http://www.qishu.cc/"+cate[i-1]+"/list"+i+"_"+j+".html";
                SwqDownloadNovel t1 = new SwqDownloadNovel(url,cate[i-1]);
                //执行任务并获取Future对象
                Future<List> future  = executor.submit(t1);
                list.add(future);
            }
        executor.shutdown();
        while(true){
            if(executor.isTerminated()){
                for (Future<List> f:list){
                    service.insertBatchForNovel(f.get());
                }
                break;
            }
        }
        long b = System.currentTimeMillis();
        System.out.println("一共耗时："+(b-a)+"ms");
    }

    public static void main(String[] arg){
        UserService user = FrameSpringBeanUtil.getBean(UserService.class);
        List list = user.queryList();
    }

    @ResponseBody
    @RequestMapping("/query")
    public String query() {
        List list = service.queryList();
        System.out.println(list.size());
        return  null;
    }
    @ResponseBody
    @RequestMapping("/queryOneGroup")
    public List queryOneGroup(){
        return service.createNum();
    }
}

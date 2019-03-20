package com.swq.service.impl;

import com.swq.action.BonusThread;
import com.swq.dao.UserDao;
import com.swq.service.UserService;
import com.swq.util.ListOperationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 用户信息业务实现类
 *
 * @author swq
 * 2018-07-12 10:10
 */
@Service
public class UserServiceImpl implements UserService {
    Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao dao;

    public List money (){
        ArrayList<Integer> arr = new ArrayList();
        arr.add((int)(Math.random()*35)+1);
        int b = getNumArea(35,arr);
        arr.add(b);
        int c = getNumArea(35,arr);
        arr.add(c);
        int d = getNumArea(35,arr);
        arr.add(d);
        int e = getNumArea(35,arr);
        arr.add(e);
        Comparator com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                if((int)o1>(int)o2)
                    return 1;
                else return -1;
            }
        };
        arr.sort(com);

        ArrayList<Integer> arr2 = new ArrayList();
        arr2.add((int)(Math.random()*12)+1);
        int g=getNumArea(12,arr2);
        arr2.add(g);
        arr2.sort(com);

        dao.insertNum(
                arr.get(0),arr.get(1),arr.get(2),arr.get(3),
                arr.get(4),arr2.get(0),arr2.get(1),1,1);
        List result = new ArrayList();
        result.add(arr.get(0));
        result.add(arr.get(1));
        result.add(arr.get(2));
        result.add(arr.get(3));
        result.add(arr.get(4));
        result.add(arr2.get(0));
        result.add(arr2.get(1));
        return result;
    }

    @Override
    public void insertBatch(List<Map> item) {
        dao.insertBatch(item);
    }

    @Override
    public String createMoreNum() {
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
                try{
                    for (Future<List<Map>> f:list){
                        resultList.addAll(f.get());
                    }
                    System.out.println("所有的子线程都结束了！");
                }catch (InterruptedException e){
                    System.out.print("有线程还需要点时间才能完成！");
                }catch (ExecutionException e){
                    System.out.println("线程内出错，线程中断");
                }
                break;
            }
        }
        List<List<Map>> splitList = ListOperationUtil.splitList(resultList,5000);
        for (List<Map> item : splitList) {
            dao.insertBatch(item);
        }
        long b = System.currentTimeMillis();
        System.out.println("success");
        System.out.println("执行记录数："+resultList.size());
        System.out.println("耗时："+(b-a)/1000+"s");
        return "success";
    }

    @Override
    public void insertBatchForNovel(List item) {
        if(item.size()>0){
            dao.insertBatchForNovel(item);
        }
    }

    private int getNumArea(int maxNum,ArrayList arr) {
        int num ;
        do{
            num= (int)(Math.random()*maxNum)+1;
        }while (arr.contains(num));
        return num;
    }


    @Override
    public List createNum() {
        List result = new ArrayList();
        result.add(money());
        result.add(money());
        result.add(money());
        result.add(money());
        result.add(money());
        return result;
    }
}

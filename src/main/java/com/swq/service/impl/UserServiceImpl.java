package com.swq.service.impl;

import com.swq.dao.UserDao;
import com.swq.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public List createNum (){
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
        System.out.print(arr);
        System.out.print(arr2);

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

    private int getNumArea(int maxNum,ArrayList arr) {
        int num ;
        do{
            num= (int)(Math.random()*maxNum)+1;
            System.out.println(arr.contains(num));
        }while (arr.contains(num));
        return num;
    }


}

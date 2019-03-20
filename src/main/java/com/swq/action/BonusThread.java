package com.swq.action;

import com.swq.service.UserService;
import com.swq.util.FrameSpringBeanUtil;
import com.swq.util.ID_GENERATION;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * ${todo}(这里一句话描述这个类的作用)
 *
 * @author ShaoWenQiang
 * @date 2019/1/23 0023 上午 10:25
 */
public class BonusThread implements Callable<List<Map>> {
    private String param;

    public BonusThread(String s) {
        this.param = s;
    }

    @Override
    public List<Map> call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "正在执行");
        long a = System.currentTimeMillis();
        List<Map> result = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            result.add(this.createNum());
        }
        long b = System.currentTimeMillis();
        System.out.println("耗时" + (b - a) / 1000 + "s");
        System.out.println(Thread.currentThread().getName() + "执行完毕");
        return result;
    }

    public Map createNum() {
        ArrayList<Integer> arr = new ArrayList();
        arr.add((int) (Math.random() * 35) + 1);
        int b = getNumArea(35, arr);
        arr.add(b);
        int c = getNumArea(35, arr);
        arr.add(c);
        int d = getNumArea(35, arr);
        arr.add(d);
        int e = getNumArea(35, arr);
        arr.add(e);
        Comparator com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                if ((int) o1 > (int) o2)
                    return 1;
                else return -1;
            }
        };
        arr.sort(com);
        ArrayList<Integer> arr2 = new ArrayList();
        arr2.add((int) (Math.random() * 12) + 1);
        int g = getNumArea(12, arr2);
        arr2.add(g);
        arr2.sort(com);
        Map item = new LinkedHashMap();
        item.put("id", ID_GENERATION.UUID_ID());
        item.put("n0", this.frontCompWithZore(arr.get(0),2));
        item.put("n1", this.frontCompWithZore(arr.get(1),2));
        item.put("n2", this.frontCompWithZore(arr.get(2),2));
        item.put("n3", this.frontCompWithZore(arr.get(3),2));
        item.put("n4", this.frontCompWithZore(arr.get(4),2));
        item.put("n5", this.frontCompWithZore(arr2.get(0),2));
        item.put("n6", this.frontCompWithZore(arr2.get(1),2));
        item.put("n7", "1");
        return item;
    }

    private int getNumArea(int maxNum, ArrayList arr) {
        int num;
        do {
            num = (int) (Math.random() * maxNum) + 1;
        } while (arr.contains(num));
        return num;
    }

    public static String frontCompWithZore(int sourceDate, int formatLength) {
        /*
         * 0 指前面补充零
         * formatLength 字符总长度为 formatLength
         * d 代表为正数。
         */
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }
}

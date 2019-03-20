package com.swq.util;

/**
 * ${todo}(这里一句话描述这个类的作用)
 *
 * @author ShaoWenQiang
 * @date 2019/1/24 0024 下午 3:46
 */
public class JDK_API_NEW {
    public static void main(String[] args){
        System.out.println("我是main方法");
        getNewApi();
    }

    public static String getNewApi(){
        String isBlank = " ";
        System.out.println(isBlank.isEmpty());
        return "success";
    }
}

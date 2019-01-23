package com.swq.util;

import java.util.UUID;

/**
 * 统一管理ID生成方法
 *
 * @author swq
 * @date 2018-07-16 14:15
 */
public class ID_GENERATION {
    /**
     * 返回由UUID生成的32位字符串（去掉“-”）作为ID
     * @return UUID_ID
     */
    public static String UUID_ID(){
       return UUID.randomUUID().toString().replace("-","");
    }
}

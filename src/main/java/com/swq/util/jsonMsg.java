package com.swq.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.*;

/**
 * 统一管理action返回 json 数据
 *
 * @author hq4
 * @date 2017年10月09日 下午08:08:31
 */
public class jsonMsg {
    /**
     * 消息封装
     * true ==>> success false ==>> erro
     *
     * @param b 状态
     * @return Map
     */
    public static Map booleanToMap(boolean b) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("state", "error");
        if (b) {
            m.put("state", "success");
        }
        return m;
    }

    /**
     * 消息封装
     *
     * @param state "success" 或 "error"
     * @param object 成功信息或失败提示
     * @return Map
     */
    public static Map toJosn(String state, Object object) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("state", state);
        m.put("msg", object);
        return m;
    }

    /**
     * 统一确认消息封装
     *
     * @param state    状态 boolean true成功 false 失败
     * @param trueMsg  成功信息
     * @param falseMsg 失败信息
     * @return Map
     */
    public static Map<String, Object> msgByBoolean(boolean state, Object trueMsg, Object falseMsg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (state) {
            resultMap.put("state", "success");
            if (null != trueMsg) {
                resultMap.put("msg", trueMsg);
            }
        } else {
            resultMap.put("state", "error");
            if (null != falseMsg) {
                resultMap.put("msg", falseMsg);
            }
        }
        return resultMap;
    }

    /**
     * 创建e-charts使用的数据格式
     * @return Map
     */
    public static Map<String, List> creatMap() {
        Map<String, List> map = new HashMap<>();
        map.put("total", new ArrayList());
        map.put("miss", new ArrayList());
        map.put("xdata", new ArrayList());
        map.put("ydata", new ArrayList());
        map.put("yaxis", new ArrayList());
        map.put("data", new ArrayList());
        map.put("legend", new ArrayList());
        map.put("start", new ArrayList());
        map.put("end", new ArrayList());
        return map;
    }

    /**
     * 将List封装为EasyUI列表  需使用PageHelper
     * @param list pageHelpe List
     * @return Map 符合EasyUI Table 的数据样式
     */
    public static Map<String, Object> msgByListForEasyuiTable(List list) {
        return isPage(list);
    }
    /**
     * 将List封装为Bootstrap列表
     * @param list pageHelpe List
     * @return Map 符合BootstrapTable 的数据样式
     */
    public static Map<String,Object> msgByListForBootstrap(List list) {
        return isPage(list);
    }

    public static Map isPage(List list){
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if(list instanceof Page){
            PageInfo pageInfo = new PageInfo<>(list);
            resultMap.put("total", pageInfo.getTotal());
            resultMap.put("rows", pageInfo.getList()==null?new ArrayList<>():pageInfo.getList());
        }else{
            resultMap.put("total", list.size());
            resultMap.put("rows", list==null?new ArrayList<>():list);
        }
        return resultMap;
    }


}

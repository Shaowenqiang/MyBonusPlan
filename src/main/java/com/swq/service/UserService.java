package com.swq.service;



import java.util.List;
import java.util.Map;

/**
 * 此接口为用户信息业务接口
 *
 * @author swq
 * @date 2018-07-12 10:01
 */
public interface UserService {
    List createNum();

    void insertBatch(List<Map> item);

    List queryList();

    String createMoreNum();

    void insertBatchForNovel(List item);
}

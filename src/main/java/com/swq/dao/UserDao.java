package com.swq.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户信息操作数据库接口
 *
 * @author swq
 * @date 2018-07-12 10:07
 */
@Repository
public interface UserDao {
    /**
     * @param integer
     * @param integer1
     * @param integer2
     * @param integer3
     * @param integer4
     * @param integer5
     * @param integer6
     * @param integer7
     * @param i
     */
    void insertNum(@Param("n0") Integer integer, @Param("n1") Integer integer1,
                   @Param("n2") Integer integer2, @Param("n3") Integer integer3,
                   @Param("n4") Integer integer4, @Param("n5") Integer integer5,
                   @Param("n6") Integer integer6, @Param("n7") Integer integer7,
                   @Param("n8") Integer i);

    /**
     * @param item
     */
    void insertBatch(List<Map> item);

    /**
     * @return
     */
    List queryList();

    /**
     * @param item
     */
    void insertBatchForNovel(List<Map> item);
}

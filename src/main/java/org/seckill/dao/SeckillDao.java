package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: org.seckill.dao</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/14 18:57
 * Description: No Description
 */
public interface SeckillDao {

    /**
     * 减库存操作
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1，表示更新的记录行数
     */
    int reduceNumber (@Param("seckillId") long seckillId, @Param("killTime") Date killTime);


    /**
     * 根据商品ID查询某个秒杀商品
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);


    /**
     * 根据偏移量查询秒杀商品列表
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offet") int offet, @Param("limit") int limit);
}

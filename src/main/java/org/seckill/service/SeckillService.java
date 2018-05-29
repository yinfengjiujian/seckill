package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * <p>Title: org.seckill.service</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/15 23:21
 * Description: No Description
 */
public interface SeckillService {

    /**
     * 查询所有的秒杀List记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个商品的秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(long seckillId);

    /**
     * 某个商品秒杀开启时返回商品的秒杀地址
     * 否则返回系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exposertSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException;


}

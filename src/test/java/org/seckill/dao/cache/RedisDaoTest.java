package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.seckill.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * <p>Title: org.seckill.dao.cache</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/18 2:03
 * Description: No Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:springConfig/spring-dao.xml",
        "classpath:springConfig/spring-service.xml"})
public class RedisDaoTest {

    private long seckillId = 1000;

    private static final String cacheStr = "seckill:";

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() {
        Seckill seckill = (Seckill) redisOperator.get(cacheStr+seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill != null) {
                redisOperator.set(cacheStr+seckillId,seckill);
                seckill = (Seckill) redisOperator.get(cacheStr+seckillId);
                System.out.println(seckill);
            }
        }
    }
}
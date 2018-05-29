package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * <p>Title: org.seckill.service</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/16 16:43
 * Description: No Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
            "classpath:springConfig/spring-dao.xml",
            "classpath:springConfig/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getSeckillById() {
        long seckillId = 1000;
        Seckill seckill = seckillService.getSeckillById(seckillId);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void testSeckillLogic(){
        long seckillId = 1000;
        Exposer exposer = seckillService.exposertSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            //秒杀已经开启
            logger.info("exposer={}",exposer);
            long phone = 13876278465L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId,phone,md5);
                logger.info("result={}",seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        }else {
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }
    }
}
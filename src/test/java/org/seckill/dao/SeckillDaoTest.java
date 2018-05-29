package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: org.seckill.dao</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/15 18:28
 * Description: 前提是需要配置spring和Junit整合
 * Junit启动时加载SpringIOC容器
 */
/*1、Junit整合Spring步骤   加载SpringJUnit4ClassRunner.class
* 2、告诉Junit关于Spring的配置文件
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springConfig/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
       List<Seckill> seckillList = seckillDao.queryAll(0,100);
        for (Seckill seckill:seckillList) {
            System.out.println(seckill);
        }
    }

    @Test
    public void reduceNumber() {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000,killTime);
        System.out.println("updateCount:"+updateCount);
    }


}
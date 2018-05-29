package org.seckill.service.Impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.seckill.utils.RedisOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: org.seckill.service.Impl</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/16 0:04
 * Description: 秒杀Service接口实现类
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String CACHE_KEY = "seckill:";

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisOperator redisOperator;

    //md5盐值字符串，用于浑浊MD5
    private final String slat = "fjwofeiwa347892^(*&^%%$@!#!@)*(GFY%&*%**(*#@";


    /**
     * 查询所有的秒杀List记录
     *
     * @return
     */
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    /**
     * 查询单个商品的秒杀记录
     *
     * @param seckillId
     * @return
     */
    public Seckill getSeckillById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /**
     * 某个商品秒杀开启时返回商品的秒杀地址
     * 否则返回系统时间和秒杀时间
     *
     * @param seckillId
     */
    public Exposer exposertSeckillUrl(long seckillId) {
        //从redis缓存中取返回秒杀对象
        Seckill seckill = (Seckill) redisOperator.get(CACHE_KEY + seckillId);
        if (seckill == null) {
            //如果缓存中没有，则从数据库中去取秒杀对象
            seckill = this.getSeckillById(seckillId);
            //如果秒杀商品为空，则直接返回
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                //存入redis缓存中
                redisOperator.set(CACHE_KEY + seckillId, seckill);
            }
        }
        //如果不为空，则执行秒杀商品信息返回
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(),
                    startTime.getTime(), endTime.getTime());
        }
        //转化为特定字符串的过程，不可逆
        String md5 = this.getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }


    /**
     * 生成MD5的加密
     *
     * @param seckillId
     * @return
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 执行秒杀操作
     * <p>
     * <p>
     * 使用注解控制事务方法的优点：
     * 1、开发团队达成一致约定，明确所有事务管理都通过注解方式方法的编程风格--->即  约定大于配置 的思想；
     * 2、整个事务处理方法执行时间尽可能短，不要穿插其它网络操作如：RPC/http请求，整个事务处理的方法尽可能只包含
     * 对数据库的操作要干净，其它的调用服务计算都剥离到方法外执行；
     * 3、不是所有的方法都需要事务管理，如只有一条数据修改操作，只读操作不需要事务管理控制；
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || !md5.equals(this.getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrited");
        }
        //执行秒杀操作业务逻辑：减库存+记录秒杀明细
        Date nowTime = new Date();
        try {
            //减库存操作
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录,秒杀结束
                throw new SeckillCloseException("seckill is closed");
            } else {
                //秒杀成功，记录秒杀明细记录
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                //唯一主键：seckillId,userPhone
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException closeE) {
            throw closeE;
        } catch (RepeatKillException repeatE) {
            throw repeatE;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常 转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}

package org.seckill.service;

import org.seckill.entity.TOrder0;

/**
 * <p>Title: org.seckill.service</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/6/10 8:49
 * Description: No Description
 */
public interface OrderService {

    TOrder0 getOrderById(Long order_id);
}

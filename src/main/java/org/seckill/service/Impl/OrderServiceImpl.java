package org.seckill.service.Impl;

import org.seckill.dao.TOrder0Mapper;
import org.seckill.entity.TOrder0;
import org.seckill.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: org.seckill.service.Impl</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/6/10 8:50
 * Description: No Description
 */
@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TOrder0Mapper tOrder0Mapper;

    public TOrder0 getOrderById(Long order_id) {
        return tOrder0Mapper.selectByPrimaryKey(order_id);
    }

    public int insert(TOrder0 record) {
        return tOrder0Mapper.insert(record);
    }
}

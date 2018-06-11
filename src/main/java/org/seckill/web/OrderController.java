package org.seckill.web;

import org.seckill.dto.SeckillResult;
import org.seckill.entity.TOrder0;
import org.seckill.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title: org.seckill.web</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/6/10 8:38
 * Description: No Description
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    /**
     * 获取秒杀对象的列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<TOrder0> getList() {
        SeckillResult<TOrder0> result;
        Long order_id = 3238L;
        TOrder0 order = null;
        try {
            order = orderService.getOrderById(order_id);
            result = new SeckillResult<TOrder0>(true, order);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return result;
    }

    /**
     * 获取秒杀对象的列表
     *
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<TOrder0> insertT() {
        SeckillResult<TOrder0> result;
        TOrder0 tOrder0 = new TOrder0();
        tOrder0.setOrderId(3239L);
        tOrder0.setUserId(20);
        tOrder0.setStatus("1");
        orderService.insert(tOrder0);
        result = new SeckillResult<TOrder0>(true, tOrder0);
        return result;
    }

}

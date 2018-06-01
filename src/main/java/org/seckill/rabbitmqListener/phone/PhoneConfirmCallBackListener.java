package org.seckill.rabbitmqListener.phone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * <p>Title: org.seckill.rabbitmqListener.phone</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/31 12:03
 * Description: No Description
 */
public class PhoneConfirmCallBackListener implements ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("phone------------------>confirm--:correlationData:" + correlationData + ",ack:" + ack + ",cause:" + cause);
    }
}

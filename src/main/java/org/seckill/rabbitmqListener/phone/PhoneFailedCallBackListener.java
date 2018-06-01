package org.seckill.rabbitmqListener.phone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

/**
 * <p>Title: org.seckill.rabbitmqListener.phone</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/31 12:04
 * Description: No Description
 */
public class PhoneFailedCallBackListener implements ReturnCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void returnedMessage(Message message, int replyCode,
                                String replyText, String exchange,
                                String routingKey) {

        logger.info("phone------------------>return--message:" +
                new String(message.getBody()) +
                ",replyCode:" + replyCode + ",replyText:" + replyText +
                ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}

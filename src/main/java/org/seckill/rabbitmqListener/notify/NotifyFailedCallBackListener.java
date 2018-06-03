package org.seckill.rabbitmqListener.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

/**
 * <p>Title: org.seckill.rabbitmqListener.notify</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/6/3 0:28
 * Description: 延迟任务测试----> 消息发送失败回调类
 */
public class NotifyFailedCallBackListener implements ReturnCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Returned message callback.
     *
     * @param message    the returned message.
     * @param replyCode  the reply code.
     * @param replyText  the reply text.
     * @param exchange   the exchange.
     * @param routingKey the routing key.
     */
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("延迟测试------------->return--message:" +
                new String(message.getBody()) +
                ",replyCode:" + replyCode + ",replyText:" + replyText +
                ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}

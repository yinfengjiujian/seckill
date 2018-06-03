package org.seckill.rabbitmqListener.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * <p>Title: org.seckill.rabbitmqListener.notify</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/6/3 0:27
 * Description: 延迟任务测试--->消息确认回调类
 */
public class NotifyConfirmCallBackListener implements ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Confirmation callback.
     *
     * @param correlationData correlation data for the callback.
     * @param ack             true for ack, false for nack
     * @param cause           An optional cause, for nack, when available, otherwise null.
     */
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("延迟测试---确认消息完成-------->confirm--:correlationData:" + correlationData.getId() + ",ack:" + ack + ",cause:" + cause);
    }
}

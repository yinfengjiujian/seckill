package org.seckill.rabbitmqListener.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * <p>Title: org.seckill.rabbitmqListener</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/30 17:59
 * Description: 消费则处理消息后，返回消息处理状态，服务器端进行消息确认的回调处理类
 */
public class MailConfirmCallBackListener implements ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        logger.info("mail---确认消息完成-------->confirm--:correlationData:" + correlationData.getId() + ",ack:" + ack + ",cause:" + cause);
    }
}

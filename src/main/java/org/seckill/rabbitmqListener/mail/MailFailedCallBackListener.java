package org.seckill.rabbitmqListener.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

/**
 * <p>Title: org.seckill.rabbitmqListener</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/30 18:03
 * Description: 回调处理类：消息在发送到RabbitMQ服务器过程中，没有成功入队列或者失败了，则回调处理
 */
public class MailFailedCallBackListener implements ReturnCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void returnedMessage(Message message, int replyCode,
                                String replyText, String exchange,
                                String routingKey) {

        logger.info("Mail------------->return--message:" +
                new String(message.getBody()) +
                ",replyCode:" + replyCode + ",replyText:" + replyText +
                ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}

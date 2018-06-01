package org.seckill.utils.rabbitmq.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.seckill.utils.rabbitmq.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * <p>Title: org.seckill.utils.rabbitmq.Impl</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/30 11:50
 * Description: 消息生产者的操作类
 */
@Component
public class PhoneProducerImpl implements MQProducer {

    private static final Logger logger = LoggerFactory.getLogger(MailProducerImpl.class);

    @Autowired
    private RabbitTemplate phoneRabbitTemplate;

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a default routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param message
     */
    public void sendDataToRabbitMQ(Object message) {
        try {
            if (message instanceof Message){
                Message Sendmessage = (Message) message;
                String msgId = Sendmessage.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                phoneRabbitTemplate.convertAndSend(phoneRabbitTemplate.getRoutingKey(),message,correlationData);
            }else {
                phoneRabbitTemplate.convertAndSend(message);
            }
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a default routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param message
     * @param messagePostProcessor
     */
    public void sendDataToRabbitMQ(Object message, MessagePostProcessor messagePostProcessor) {
        try {
            if (message instanceof Message){
                Message Sendmessage = (Message) message;
                String msgId = Sendmessage.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                phoneRabbitTemplate.convertAndSend(phoneRabbitTemplate.getRoutingKey(),message,messagePostProcessor,correlationData);
            }else {
                phoneRabbitTemplate.convertAndSend(message, messagePostProcessor);
            }
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a default routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param message
     * @param messagePostProcessor
     * @param correlationData
     */
    public void sendDataToRabbitMQ(Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) {
        try {
            phoneRabbitTemplate.convertAndSend(message, messagePostProcessor, correlationData);
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param routingKey
     * @param message
     */
    public void sendDataToRabbitMQ(String routingKey, Object message) {
        try {
            if (message instanceof Message){
                Message Sendmessage = (Message) message;
                String msgId = Sendmessage.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                phoneRabbitTemplate.convertAndSend(routingKey,message,correlationData);
            }else {
                phoneRabbitTemplate.convertAndSend(routingKey, message);
            }
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param routingKey
     * @param message
     * @param correlationData
     */
    public void sendDataToRabbitMQ(String routingKey, Object message, CorrelationData correlationData) {
        try {
            phoneRabbitTemplate.convertAndSend(routingKey, message, correlationData);
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param routingKey
     * @param message
     * @param messagePostProcessor
     */
    public void sendDataToRabbitMQ(String routingKey, Object message, MessagePostProcessor messagePostProcessor) {
        try {
            if (message instanceof Message){
                Message Sendmessage = (Message) message;
                String msgId = Sendmessage.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                phoneRabbitTemplate.convertAndSend(routingKey,message,messagePostProcessor,correlationData);
            }else {
                phoneRabbitTemplate.convertAndSend(routingKey, message, messagePostProcessor);
            }
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param routingKey
     * @param message
     * @param messagePostProcessor
     * @param correlationData
     */
    public void sendDataToRabbitMQ(String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) {
        try {
            phoneRabbitTemplate.convertAndSend(routingKey, message, messagePostProcessor, correlationData);
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a specific exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void sendDataToRabbitMQ(String exchange, String routingKey, Object message) {
        try {
            if (message instanceof Message){
                Message Sendmessage = (Message) message;
                String msgId = Sendmessage.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                phoneRabbitTemplate.convertAndSend(routingKey,message,correlationData);
            }else {
                phoneRabbitTemplate.convertAndSend(exchange, routingKey, message);
            }
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a specific exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param exchange
     * @param routingKey
     * @param message
     * @param correlationData
     */
    public void sendDataToRabbitMQ(String exchange, String routingKey, Object message, CorrelationData correlationData) {
        try {
            phoneRabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a specific exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param exchange
     * @param routingKey
     * @param message
     * @param messagePostProcessor
     */
    public void sendDataToRabbitMQ(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor) {
        try {
            phoneRabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor);
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a specific exchange with a specific routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param exchange
     * @param routingKey
     * @param message
     * @param messagePostProcessor
     * @param correlationData
     */
    public void sendDataToRabbitMQ(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) {
        try {
            phoneRabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor, correlationData);
        } catch (AmqpException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 构建Message对象，进行消息发送
     * @param handleObject
     * @param msgId
     * @return
     */
    public Message messageBuil(Object handleObject, String msgId) {
        try {
            //先转成JSON
            String objectJSON = JSON.toJSONString(handleObject);
            //再构建Message对象
            Message messageBuil = MessageBuilder.withBody(objectJSON.getBytes("UTF-8")).setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                    .setCorrelationId(msgId).build();
            return messageBuil;
        } catch (UnsupportedEncodingException e) {
            logger.error("构建Message出错：" + e.getMessage(),e);
            return null;
        }
    }

    /**
     * 生成唯一的消息操作id
     * @return
     */
    public String getMsgId() {
        return UUID.randomUUID().toString();
    }
}

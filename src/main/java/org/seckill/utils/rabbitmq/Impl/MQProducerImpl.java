package org.seckill.utils.rabbitmq.Impl;

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
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * <p>Title: org.seckill.utils.rabbitmq.Impl</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/6/2 22:54
 * Description: 消息生产者操作主体类
 */
@Component
public class MQProducerImpl implements MQProducer{

    private static final Logger logger = LoggerFactory.getLogger(MQProducerImpl.class);

    private RabbitTemplate rabbitTemplate;

    /**
     * Sets the rabbitTemplate.
     * <p>
     * <p>You can use getRabbitTemplate() to get the value of rabbitTemplate</p>
     *
     * @param rabbitTemplate rabbitTemplate
     */
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Convert a Java object to an Amqp Message and send it to a default exchange with a default routing key.
     * 由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
     *
     * @param message
     */
    public void sendDataToRabbitMQ(Object message) {
        try {
            if (message instanceof Message){
                Message messageSend = (Message) message;
                String msgId = messageSend.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(rabbitTemplate.getRoutingKey(),message,correlationData);
            }else {
                rabbitTemplate.convertAndSend(message);
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
                Message messageSend = (Message) message;
                String msgId = messageSend.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(rabbitTemplate.getRoutingKey(),message,messagePostProcessor,correlationData);
            }else {
                rabbitTemplate.convertAndSend(message, messagePostProcessor);
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
            rabbitTemplate.convertAndSend(message, messagePostProcessor, correlationData);
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
                Message messageSend = (Message) message;
                String msgId = messageSend.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(routingKey,message,correlationData);
            }else {
                rabbitTemplate.convertAndSend(routingKey, message);
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
            rabbitTemplate.convertAndSend(routingKey, message, correlationData);
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
                Message messageSend = (Message) message;
                String msgId = messageSend.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(routingKey,message,messagePostProcessor,correlationData);
            }else {
                rabbitTemplate.convertAndSend(routingKey, message, messagePostProcessor);
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
            rabbitTemplate.convertAndSend(routingKey, message, messagePostProcessor, correlationData);
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
                Message messageSend = (Message) message;
                String msgId = messageSend.getMessageProperties().getCorrelationId();
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(routingKey,message,correlationData);
            }else {
                rabbitTemplate.convertAndSend(exchange, routingKey, message);
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
            rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
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
            rabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor);
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
            rabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor, correlationData);
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
            String objectJSON = JSONObject.toJSONString(handleObject);
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

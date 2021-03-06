package org.seckill.web;

import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.utils.rabbitmq.Impl.MQProducerImpl;
import org.seckill.utils.rabbitmq.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>Title: org.seckill.web</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/30 17:33
 * Description: 消息队列测试
 */
@Controller
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mail.exchange.key}")
    private String queue_key;

    @Value("${phone.topic.key.more}")
    private String phone_key_more;

    @Value("${phone.topic.key}")
    private String phone_key;

    @Value("${delay.directQueue.key}")
    private String delay_directQueue_key;

    @Value("${delay.directMessage.key}")
    private String delay_directMessage_key;

    @Autowired
    private MQProducerImpl mailMQProducerImpl;

    @Autowired
    private MQProducerImpl phoneMQProducerImpl;

    @Autowired
    private MQProducerImpl delayMQProducerImpl;

    /**
     * @Description: 消息队列
     * @Author:
     * @CreateTime:
     */
    @ResponseBody
    @RequestMapping("/sendMailQueue")
    public SeckillResult<Long> testMailQueue() {
        SeckillResult<Long> result = null;
        Date now = new Date();
        try {
            Seckill seckill = new Seckill();
            seckill.setSeckillId(193393287);
            seckill.setName("段美林");
            seckill.setNumber(200);

            String msgId = mailMQProducerImpl.getMsgId();
            Message message = mailMQProducerImpl.messageBuil(seckill,msgId);
            if (message != null) {
                message.getMessageProperties().setExpiration(String.valueOf(60000));
                mailMQProducerImpl.sendDataToRabbitMQ(queue_key, message);
            }
            result = new SeckillResult<Long>(true, now.getTime());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @Description: 消息队列
     * @Author:
     * @CreateTime:
     */
    @ResponseBody
    @RequestMapping("/sendPhoneQueue")
    public SeckillResult<Long> testPhoneQueue() {
        SeckillResult<Long> result = null;
        Date now = new Date();
        try {
            Seckill seckill = new Seckill();

            for (int i = 0; i < 10; i++) {
                seckill.setSeckillId(1922339387 + i);
                seckill.setName("caijuan" + i);
                String msgId = phoneMQProducerImpl.getMsgId();
                Message message = phoneMQProducerImpl.messageBuil(seckill,msgId);
                phoneMQProducerImpl.sendDataToRabbitMQ(phone_key, message);
            }
            result = new SeckillResult<Long>(true, now.getTime());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @Description: 消息队列
     * @Author:
     * @CreateTime:
     */
    @ResponseBody
    @RequestMapping("/sendDelayQueue")
    public SeckillResult<Long> testDelayQueue() {
        SeckillResult<Long> result = null;
        Date now = new Date();
        try {
            Seckill seckill = new Seckill();

            for (int i = 0; i < 2; i++) {
                seckill.setSeckillId(1922339387 + i);
                seckill.setName("delay_queue_ttl_" + i);
                String msgId = delayMQProducerImpl.getMsgId();
                Message message = delayMQProducerImpl.messageBuil(seckill,msgId);
                delayMQProducerImpl.sendDataToRabbitMQ(delay_directQueue_key, message);
            }

            for (int i = 0; i < 2; i++) {
                seckill.setSeckillId(1922339287 + i);
                seckill.setName("delay_message_ttl_" + i);
                String msgId = delayMQProducerImpl.getMsgId();
                Message message = delayMQProducerImpl.messageBuil(seckill,msgId);
                if (message != null) {
                    //给消息设置过期时间ttl，为3分钟
                    message.getMessageProperties().setExpiration("180000");
                    delayMQProducerImpl.sendDataToRabbitMQ(delay_directMessage_key, message);
                }
            }
            result = new SeckillResult<Long>(true, now.getTime());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

}

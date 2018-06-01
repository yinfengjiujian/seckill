package org.seckill.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.utils.rabbitmq.Impl.MailProducerImpl;
import org.seckill.utils.rabbitmq.Impl.PhoneProducerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

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

    @Autowired
    private MailProducerImpl mailProducer;

    @Autowired
    private PhoneProducerImpl phoneProducer;

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

            String msgId = mailProducer.getMsgId();
            Message message = mailProducer.messageBuil(seckill,msgId);
            if (message != null) {
                mailProducer.sendDataToRabbitMQ(queue_key, message);
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
                String msgId = phoneProducer.getMsgId();
                Message message = phoneProducer.messageBuil(seckill,msgId);
                phoneProducer.sendDataToRabbitMQ(phone_key, message);
            }
            result = new SeckillResult<Long>(true, now.getTime());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

}

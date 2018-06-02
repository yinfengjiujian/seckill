package org.seckill.utils.rabbitmq.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.seckill.utils.rabbitmq.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: org.seckill.utils.rabbitmq.Impl</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/30 12:01
 * Description: 测试发送消息到rabbitmq服务器上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springConfig/spring-*.xml"})
public class MailProducerImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    final String queue_key = "study_queue_key";

    @Autowired
    private MQProducer mailMQProducerImpl;

    @Test
    public void sendDataToQueue() {
        Map<String,Object> msg = new HashMap();
        msg.put("data","hello,rabbmitmq!");
        mailMQProducerImpl.sendDataToRabbitMQ(queue_key,msg);

        Seckill seckill = new Seckill();
        seckill.setName("duanml");
        seckill.setSeckillId(102020239);
        mailMQProducerImpl.sendDataToRabbitMQ(queue_key,seckill);

    }
}
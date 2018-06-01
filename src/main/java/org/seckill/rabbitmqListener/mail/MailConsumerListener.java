package org.seckill.rabbitmqListener.mail;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * <p>Title: org.seckill.rabbitmqListener</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/5/30 17:55
 * Description: 消费方实现类
 */
public class MailConsumerListener implements ChannelAwareMessageListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            logger.info("mail--消费开始----->consumer--:" + message.getMessageProperties() + ":" + new String(message.getBody(),"UTF-8"));


            //TODO 进行相关业务操作

            //成功处理业务，那么返回消息确认机制，这个消息成功处理OK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                //消息已经进行过一次轮询操作，还是失败，将拒绝再次接收本消息
                logger.info("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息

                //TODO 进行相关业务操作

            } else {
                //消息第一次接收处理失败后，将再此回到队列中进行  再一次轮询操作
                logger.info("消息即将再次返回队列处理...");
                //处理失败，那么返回消息确认机制，这个消息没有成功处理，返回到队列中
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}

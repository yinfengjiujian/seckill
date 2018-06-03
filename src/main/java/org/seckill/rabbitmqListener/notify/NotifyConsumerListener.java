package org.seckill.rabbitmqListener.notify;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * <p>Title: org.seckill.rabbitmqListener.notify</p>
 * <p>Company:东软集团(neusoft)</p>
 * <p>Copyright:Copyright(c)2018</p>
 * User: 段美林
 * Date: 2018/6/3 0:27
 * Description: 订单通知队列监听服务
 * 实现延迟任务的功能
 */
public class NotifyConsumerListener implements ChannelAwareMessageListener {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Callback for processing a received Rabbit message.
     * <p>Implementors are supposed to process the given Message,
     * typically sending reply messages through the given Session.
     *
     * @param message the received AMQP message (never <code>null</code>)
     * @param channel the underlying Rabbit Channel (never <code>null</code>)
     * @throws Exception Any.
     */
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            //将字节流对象转换成Java对象
//            Seckill seckill=(Seckill) new ObjectInputStream(new ByteArrayInputStream(message.getBody())).readObject();

            String returnStr = new String(message.getBody(),"UTF-8");
            JSONObject jsStr = JSONObject.parseObject(returnStr);

            logger.info("延迟测试--消费开始：名称为--===>" + jsStr.getString("name") + "----->返回消息:" + returnStr + "||||消息的Properties：--》" + message.getMessageProperties());

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

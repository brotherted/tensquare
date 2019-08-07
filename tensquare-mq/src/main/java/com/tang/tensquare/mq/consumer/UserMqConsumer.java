package com.tang.tensquare.mq.consumer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserMqConsumer {

	@RabbitListener(queues = "moblie_queue")
    public void handleMessage(Map message) throws Exception {
        // 处理消息
		String mobile = (String) message.get("mobile");
		String code = (String) message.get("code");
        System.out.println(mobile + "   " + code);
    }
}

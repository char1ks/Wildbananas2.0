package com.example.SpringRedisPet_20december.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageListener implements MessageListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = message.toString();
        System.out.println(msg);
        messagingTemplate.convertAndSend("/topic/fruits", msg); // Отправляем сообщение на клиент
    }
}
package com.sumitv.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleStringConsumer {

    @KafkaListener(topics = "topic_for_group_consumer", groupId = "group_1")
    public void testConsumerG1(String message) {

        System.out.println("G1 - Message Received  "+message);
    }


}

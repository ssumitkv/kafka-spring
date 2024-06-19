package com.sumitv.kafka.consumers;

import com.sumitv.kafka.entities.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GreetingsConsumer {

    @KafkaListener(topics = "topic_greetings", groupId = "group_1")
    public void greetingsConsumer(Greeting message) {

        System.out.println("GREETINGS - Message Received  "+message);
    }


}

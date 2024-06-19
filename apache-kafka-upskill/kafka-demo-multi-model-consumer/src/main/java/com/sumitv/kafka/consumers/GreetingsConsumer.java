package com.sumitv.kafka.consumers;

import com.sumitv.kafka.entities.Greetings;
import com.sumitv.kafka.entities.User;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multi_type_id_2", topics = "multi_type_topic_2")
public class GreetingsConsumer {

    @KafkaHandler
    public void greetingsConsumer(Greetings message) {

        System.out.println("GREETINGS - Message Received  "+message);
    }

    @KafkaHandler
    public void greetingsConsumer(User user) {
        System.out.println("User - Message Received  "+user);
    }

    @KafkaHandler
    public void greetingsConsumer(Object message) {

        System.out.println("Object - Message Received  "+message);
    }


}

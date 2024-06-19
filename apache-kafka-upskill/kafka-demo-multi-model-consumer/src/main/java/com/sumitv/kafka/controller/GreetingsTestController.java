package com.sumitv.kafka.controller;

import com.sumitv.kafka.entities.Greetings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingsTestController {

    @Autowired
    @Qualifier("simpleCustomMessageTemplate")
    KafkaTemplate<String, Greetings> kafkaTemplate;


    @PostMapping("/test")
    public String test(@RequestBody Greetings message) {
        kafkaTemplate.send("multi_type_topic_2", new Greetings( message.getMessage(), message.getName()));
        return "success";
    }




}

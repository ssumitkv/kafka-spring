package com.sumitv.kafka.controller;

import com.sumitv.kafka.entities.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingsTestController {

    @Autowired
    @Qualifier("simpleCustomMessageTemplate")
    KafkaTemplate<String, Greeting> kafkaTemplate;


    @PostMapping("/test")
    public String test(@RequestBody Greeting message) {
        kafkaTemplate.send("topic_greetings", message);
        return "success";
    }




}

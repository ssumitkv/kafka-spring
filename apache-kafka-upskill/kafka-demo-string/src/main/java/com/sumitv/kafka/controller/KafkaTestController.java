package com.sumitv.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

    @Autowired
    @Qualifier("simpleStringTemplate")
    KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/test")
    public String test(@RequestParam("message") String message) {
        kafkaTemplate.send("topic_for_group_consumer", message);
        return "success";
    }




}

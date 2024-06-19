package com.sumitv.kafka.configs;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sumitv.kafka.entities.Greeting;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "spring.kafka.groupId")
    String kafkaGroupId;

    @Bean
    @Qualifier("simpleStringConsumerFactory")
    public ConsumerFactory<String, Greeting> consumerFactory() {
        Map<String, Object> property = new HashMap<>();
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG,kafkaGroupId);
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, com.fasterxml.jackson.databind.JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(property, new StringDeserializer(), new JsonDeserializer<>(Greeting.class));
    }

    @Bean
    @Qualifier("simpleStringListerConsumerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Greeting> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String,Greeting> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}

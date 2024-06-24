package com.sumitv.kafka.configs;

import com.sumitv.kafka.entities.Greetings;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Bean
    @Qualifier("simpleCustomMessageProducerFactory")
    public ProducerFactory<String, Greetings> multiTypeProducerFactory(){
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        configProperties.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProperties.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        configProperties.put(JsonSerializer.TYPE_MAPPINGS,
                "greetings:com.sumitv.kafka.entities.Greetings, user:com.sumitv.kafka.entities.User");

        return new DefaultKafkaProducerFactory<>(configProperties);
    }

    @Bean
    @Qualifier("simpleCustomMessageTemplate")
    public KafkaTemplate<String, Greetings> kafkaTemplate() {
        return new KafkaTemplate<>(multiTypeProducerFactory());
    }


}

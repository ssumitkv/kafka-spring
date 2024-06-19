package com.sumitv.kafka.configs;


import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
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
    public ConsumerFactory<String, Object> multiTypeConsumerFactory() {
        Map<String, Object> property = new HashMap<>();
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG,kafkaGroupId);
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(property);
    }

    @Bean
    @Qualifier("simpleStringListerConsumerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String,Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(multiTypeConsumerFactory());
        factory.setRecordMessageConverter(multiModelConverter());
        return factory;
    }

    @Bean
    public RecordMessageConverter multiModelConverter() {
        StringJsonMessageConverter stringJsonMessageConverter = new  StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper defaultJackson2JavaTypeMapper = new DefaultJackson2JavaTypeMapper();

        defaultJackson2JavaTypeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        defaultJackson2JavaTypeMapper.addTrustedPackages("com.sumitv.kafka.entities");

        Map<String, Class<?>> mapping = new HashMap<>();
        mapping.put("com.sumitv.kafka.entities.Greetings", com.sumitv.kafka.entities.Greetings.class);
        mapping.put("com.sumitv.kafka.entities.User", com.sumitv.kafka.entities.User.class);

        defaultJackson2JavaTypeMapper.setIdClassMapping(mapping);
        stringJsonMessageConverter.setTypeMapper(defaultJackson2JavaTypeMapper);
        return stringJsonMessageConverter;
    }

}

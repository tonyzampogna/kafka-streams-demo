package com.example.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Collection;
import java.util.Map;


@Slf4j
@Configuration
public class KafkaContainerConfig {

    private static final String CONSUMER_GROUP_NAME = "simple-consumer";

    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    @Qualifier("kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            @Qualifier("kafkaListenerConsumerFactory") ConsumerFactory<String, String> defaultConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(defaultConsumerFactory);
        return factory;
    }

    @Bean("kafkaListenerConsumerFactory")
    public ConsumerFactory<String, String> kafkaListenerConsumerFactory() {
        Map<String, Object> consumerConfigs = applicationContext.getBean("consumerConfigs", Map.class);
        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs);
    }

}

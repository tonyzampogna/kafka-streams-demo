package com.example.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.kafka.config.KafkaConfig.TOPIC_NEW;
import static com.example.kafka.config.KafkaConfig.TOPIC_OLD;

@Slf4j
@Component
public class OldTopicListener {

    @KafkaListener(topics = { TOPIC_OLD }, containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<?, ?> consumerRecord) {
        log.info("OLD TOPIC -- Consumer from {} record: ", consumerRecord.topic(), consumerRecord.toString());
    }

}

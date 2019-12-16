package com.example.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.kafka.config.KafkaConfig.TOPIC_NEW;

@Slf4j
@Component
public class NewTopicListener {

    @KafkaListener(topics = { TOPIC_NEW }, containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<?, ?> consumerRecord) {
        log.info("NEW TOPIC -- Consumer from {} record: ", consumerRecord.topic(), consumerRecord.toString());
    }

}

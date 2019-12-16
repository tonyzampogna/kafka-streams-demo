package com.example.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.kafka.config.KafkaConfig.TOPIC_OLD;


@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping(path = { "/produce" })
    public String produceDefault(@RequestParam("count") Integer count) {

        for (int i = 0; i < count; i++) {
            log.info("Producing message {}", i);
            kafkaTemplate.send(TOPIC_OLD, "message " + i);
        }

        return "FINISHED";
    }

}

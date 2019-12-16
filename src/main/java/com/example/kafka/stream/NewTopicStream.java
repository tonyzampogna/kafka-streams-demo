package com.example.kafka.stream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Map;

import static com.example.kafka.config.KafkaConfig.TOPIC_NEW;
import static com.example.kafka.config.KafkaConfig.TOPIC_OLD;


@Configuration
public class NewTopicStream {

    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    public void createNewTopicStream() {
        // Get the configs
        Map<String, Object> streamsConfig = applicationContext.getBean("streamConfigs", Map.class);

        // Build a stream to stream from one topic to another.
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        streamsBuilder
                .stream(TOPIC_OLD)
                .to(TOPIC_NEW);

        // Create the KafkaStreams object and "start" it.
        Topology topology = streamsBuilder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, new KafkaStreamsConfiguration(streamsConfig).asProperties());
        // kafkaStreams.cleanUp();
        kafkaStreams.start();
    }

}

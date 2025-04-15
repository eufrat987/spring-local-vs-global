package org.example.service;

import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.Date;

public class ExampleService {

    final KafkaTemplate<Integer, String> kafkaTemplate;

    public ExampleService(final KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void commitAndSend() {
        kafkaTemplate.send("test", "test " + LocalDateTime.now());
        System.out.println("Sended");
    }

}

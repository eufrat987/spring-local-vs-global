package org.example;

import org.example.service.ExampleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

@EnableKafka
@Configuration
@ComponentScan
@EnableTransactionManagement
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        ExampleService service = context.getBean(ExampleService.class);
        service.createTable();
        System.out.println(service.readFromTable());
        try {
            service.insertAndSend(2);
        } finally {
            System.out.println(service.readFromTable());
        }
    }

    @KafkaListener(topics = "test")
    public void receive(String message) {
        System.out.println(LocalDateTime.now() + " Received: " + message);
    }

    @Bean
    public ExampleService exampleService(final KafkaTemplate<Integer, String> kafkaTemplate) {
        return new ExampleService();
    }
}
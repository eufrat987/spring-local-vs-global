package org.example;

import org.example.service.ExampleService;
import org.example.service.JmsTopicListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.concurrent.atomic.AtomicBoolean;

@EnableJms
@Configuration
@ComponentScan
@EnableTransactionManagement
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);

        var service = context.getBean(ExampleService.class);
        service.createTable();
        System.out.println(service.readFromTable());

        try {
            service.insertAndSend(1, true);
        } finally {
            System.out.println(service.readFromTable());
        }
    }

    @Bean
    public JmsTopicListener jmsTopicListener() {
        return new JmsTopicListener();
    }

    @Bean
    public ExampleService exampleService() {
        return new ExampleService();
    }
}
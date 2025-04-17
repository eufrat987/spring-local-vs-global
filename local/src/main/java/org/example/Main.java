package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.service.ExampleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        datasourceTest(context);

        ExampleService service = context.getBean(ExampleService.class);
        service.commitAndSend();
    }

    private static void datasourceTest(ApplicationContext c) {
        DataSource dataSource = c.getBean("dataSource", DataSource.class);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        // Query to fetch a single value from the database
        Integer result = jdbcTemplate.queryForObject("SELECT 2", Integer.class);

        // Print the result
        System.out.println("Result: " + result);
    }

    @KafkaListener(topics = "test")
    public void receive(String message) {
        System.out.println(LocalDateTime.now() + " Received: " + message);
    }

    @Bean
    public ExampleService exampleService(final KafkaTemplate<Integer, String> kafkaTemplate) {
        return new ExampleService(kafkaTemplate);
    }
}
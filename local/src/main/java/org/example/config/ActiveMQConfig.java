package org.example.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

@Configuration
public class ActiveMQConfig {


    @Bean
    public ConnectionFactory connectionFactory() {
        var conn = new ActiveMQConnectionFactory();
        conn.setBrokerURL("tcp://localhost:61616");
        conn.setTrustedPackages(Arrays.asList("com.mailshine.springbootstandaloneactivemq"));
        return conn;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        var jms = new JmsTemplate();
        jms.setConnectionFactory(connectionFactory());
        jms.setPubSubDomain(true);
        return jms;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }

}

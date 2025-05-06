package org.example.config;

import com.atomikos.jms.AtomikosConnectionFactoryBean;
import jakarta.jms.JMSException;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public javax.jms.ConnectionFactory connectionFactory() throws JMSException {
        var conn = new ActiveMQXAConnectionFactory();
        conn.setBrokerURL("tcp://localhost:61616");

        var atomikosConnection = new AtomikosConnectionFactoryBean();
        atomikosConnection.setLocalTransactionMode(false);
        atomikosConnection.setUniqueResourceName("xa_mq");
        atomikosConnection.setXaConnectionFactory(conn);
        atomikosConnection.setMaxPoolSize(30);
        return atomikosConnection;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        var jms = new JmsTemplate();
        jms.setConnectionFactory(connectionFactory());
        jms.setPubSubDomain(true);
        return jms;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws JMSException {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }

}

package org.example.service;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;

import java.util.ArrayList;
import java.util.List;

public class JmsTopicListener implements MessageListener {

    @Override
    @JmsListener(destination = "topic")
    public void onMessage(Message message) {
        try {
            var msg = (ActiveMQTextMessage) message;
            System.out.println("Received: " + msg.getText());
        } catch (Exception e) {
            System.out.println("Jms Consumer Error " + e);
        }
    }

}

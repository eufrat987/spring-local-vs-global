package org.example.service;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;

import java.util.ArrayList;
import java.util.List;

public class JmsTopicListener implements MessageListener {

    private List<Runnable> callbacks = new ArrayList<>();

    @Override
    @JmsListener(destination = "topic")
    public void onMessage(Message message) {
        try {
            var msg = (ActiveMQTextMessage) message;
            System.out.println("Received: " + msg.getText());
            callbacks.forEach(Runnable::run);
        } catch (Exception e) {
            System.out.println("Jms Consumer Error " + e);
        }
    }

    public void addCallback(Runnable callback) {
        callbacks.add(callback);
    }

}

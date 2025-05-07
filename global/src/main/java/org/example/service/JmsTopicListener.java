package org.example.service;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

public class JmsTopicListener implements MessageListener {

    private List<Runnable> callbacks = new ArrayList<>();

    @Override
    @Transactional
    @JmsListener(destination = "topic")
    public void onMessage(Message message) {
        try {
            var msg = (ActiveMQTextMessage) message;
            System.out.println("### Received: " + msg.getText());
            callbacks.forEach(Runnable::run);
        } catch (Exception e) {
            System.out.println("Jms Consumer Error " + e);
        }
    }

    public void addCallback(Runnable callback) {
        callbacks.add(callback);
    }

}

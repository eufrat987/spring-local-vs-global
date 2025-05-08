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

public class JmsTopicListener {


    @Transactional
    @JmsListener(destination = "topic")
    public void onMessage(String message) {
        try {
            System.out.println("### Received: " + message);
        } catch (Exception e) {
            System.out.println("Jms Consumer Error " + e);
        }
    }

}

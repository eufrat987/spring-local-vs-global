package org.example.service;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.MessageNotWriteableException;
import java.util.List;

public class ExampleService {
    private static final String TABLE_NAME = "test";
    private static final String COLUMN_NAME = "col";
    public static final String CREATE_TABLE_IF_NOT_EXISTS = "create table if not exists ";
    public static final String INSERT_INTO = "INSERT into ";
    public static final String SELECT = "SELECT ";

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTable() {
        jdbcTemplate.execute(CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + "("  + COLUMN_NAME + " int)");
    }

    @Transactional
    public void insertAndSend(int val, boolean rollback) {
        jdbcTemplate.update(INSERT_INTO + TABLE_NAME + "(" + COLUMN_NAME +") values(" + val + ")");
        jmsTemplate.convertAndSend("topic", "aloha");
        if (rollback) throw new RuntimeException("Oops!");
    }

    public List<Integer> readFromTable() {
        List<Integer> result = jdbcTemplate.queryForList(SELECT + COLUMN_NAME + " from " + TABLE_NAME, Integer.class);
        return result;
    }

}

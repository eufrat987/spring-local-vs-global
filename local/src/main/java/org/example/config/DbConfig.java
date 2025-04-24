package org.example.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DbConfig {

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:30306/mysql");
        dataSource.setUsername("root");
        dataSource.setPassword("pass");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager txManager(BasicDataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(BasicDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

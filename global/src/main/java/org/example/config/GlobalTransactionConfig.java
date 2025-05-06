package org.example.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

@Configuration
public class GlobalTransactionConfig {

    @Bean
    public UserTransaction userTransaction() {
        return new UserTransactionImp();
    }

//    @Bean
//    public TransactionManager transactionManager() {
//        return new UserTransactionManager();
//    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        var jtaTransactionManager = new JtaTransactionManager();
//        jtaTransactionManager.setTransactionManager(transactionManager());
        jtaTransactionManager.setUserTransaction(userTransaction());
        return jtaTransactionManager;
    }

}

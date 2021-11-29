package com.objectzilla;

import com.objectzilla.controller.AppController;
import com.objectzilla.controller.TransactionHistoryController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaFxConfig {
    @Bean
    public AppController appController() {
        return new AppController();
    }

    @Bean
    public TransactionHistoryController transactionHistoryController() {
        return new TransactionHistoryController();
    }
}

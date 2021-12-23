package com.objectzilla.config;

import com.objectzilla.controller.*;
import com.objectzilla.presenter.TransactionEditDialogPresenter;
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

    @Bean
    public TransactionEditDialogPresenter transactionEditDialogPresenter() {
        return new TransactionEditDialogPresenter();
    }

    @Bean
    public BarGraphController barGraphController() {
        return new BarGraphController();
    }

    @Bean
    public MainController mainController() {
        return new MainController();
    }

    @Bean
    public ControllerRepository controllerRepository() {
        return new ControllerRepository();
    }

    @Bean
    public CategoryPieChartController categoryPieChartController() {
        return new CategoryPieChartController();
    }
}

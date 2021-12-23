package com.objectzilla.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.springframework.beans.factory.annotation.Autowired;

public class MainController implements Controller {
    @FXML
    private Tab categoryPieChartTab;
    @FXML
    private Tab barGraphTab;
    @FXML
    private TabPane tabPane;

    private TransactionHistoryController transactionHistoryController;
    private BarGraphController barGraphController;
    private CategoryPieChartController categoryPieChartController;

    @FXML
    private void initialize() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == barGraphTab) {
                barGraphController.updateData(transactionHistoryController.getTransactionHistory().getTransactions());
            } else if (newValue == categoryPieChartTab) {
                categoryPieChartController.updateData(transactionHistoryController
                        .getTransactionHistory().getTransactions());
            }
        });
    }

    @Autowired
    public void setTransactionHistoryController(TransactionHistoryController transactionHistoryController) {
        this.transactionHistoryController = transactionHistoryController;
    }

    @Autowired
    public void setBarGraphController(BarGraphController barGraphController) {
        this.barGraphController = barGraphController;
    }

    @Autowired
    public void setCategoryPieChartController(CategoryPieChartController categoryPieChartController) {
        this.categoryPieChartController = categoryPieChartController;
    }
}

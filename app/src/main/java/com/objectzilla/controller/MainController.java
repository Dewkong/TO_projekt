package com.objectzilla.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;

public class MainController implements Controller {
    @FXML
    private Tab barGraphTab;
    @FXML
    private TabPane tabPane;

    @FXML
    private BorderPane transactionHistoryPage;

    @FXML
    private GridPane barGraphPage;

    private TransactionHistoryController transactionHistoryController;
    private BarGraphController barGraphController;

    @FXML
    private void initialize() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == barGraphTab) {
                barGraphController.updateData(transactionHistoryController.getTransactionHistory().getTransactions());
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
}

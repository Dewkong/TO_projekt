package com.objectzilla.controller;

import com.objectzilla.model.Bank;
import com.objectzilla.model.TransactionHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.objectzilla.model.Transaction;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionHistoryController {
    private AppController appController;

    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<Transaction, LocalDate> operationColumn;

    @FXML
    private TableColumn<Transaction, LocalDate> bookingColumn;

    @FXML
    private TableColumn<Transaction, String> titleColumn;

    @FXML
    private TableColumn<Transaction, String> nameColumn;

    @FXML
    private TableColumn<Transaction, String> accountNumberColumn;

    @FXML
    private TableColumn<Transaction, BigDecimal> amountColumn;

    @FXML
    private TableColumn<Transaction, BigDecimal> balanceColumn;

    @FXML
    private Button fileButton;

    @FXML
    private TextField pathField;

    @FXML
    private ChoiceBox<Object> bankBox;

    @FXML
    private Button openButton;

    private FileChooser fileChooser;

    private File selectedFile;

    private TransactionHistory transactionHistory;

    @FXML
    private void initialize(){
        transactionsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        operationColumn.setCellValueFactory(new PropertyValueFactory<>("operationDate"));
        bookingColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("transactioneeName"));
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("transactioneeAccountNumber"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        fileChooser = new FileChooser();
        fileButton.setOnAction(e ->{
            selectedFile = fileChooser.showOpenDialog(this.appController.getPrimaryStage());
            if (selectedFile != null){
                pathField.setText(selectedFile.getPath());
            }
        });
        bankBox.getItems().setAll(FXCollections.observableArrayList(Bank.values()));
    }

    public void setTransactionHistory(TransactionHistory transactionHistory){
        this.transactionHistory = transactionHistory;
        transactionsTable.setItems(transactionHistory.getTransactions());
    }


    @FXML
    private void handleOpenAction(ActionEvent event) {
        //TODO
        pathField.setText("");
        System.out.println("TODO click open");
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}

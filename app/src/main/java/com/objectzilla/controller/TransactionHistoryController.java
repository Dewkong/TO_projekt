package com.objectzilla.controller;

import com.objectzilla.model.Bank;
import com.objectzilla.model.Transaction;
import com.objectzilla.model.TransactionHistory;
import com.objectzilla.persistence.repository.TransactionRepository;
import com.objectzilla.service.ImporterService;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionHistoryController implements Controller {
    private AppController appController;
    private TransactionRepository transactionRepository;
    private ImporterService importerService;

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
    private ChoiceBox<Object> bankBox;

    @FXML
    private Button openButton;

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

        openButton.disableProperty().bind(bankBox.valueProperty().isNull());
        bankBox.getItems().setAll(FXCollections.observableArrayList(Bank.values()));

        transactionsTable.setItems(transactionHistory.getTransactions());
        for (Transaction transaction : transactionRepository.findAll())
            transactionHistory.addTransaction(transaction);
    }


    @Autowired
    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }


    @FXML
    private void handleOpenAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Bank CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Csv Files", "*.csv")
        );
        File bankFile = fileChooser.showOpenDialog(this.appController.getPrimaryStage());

        if (bankFile != null) {
            importerService.importFromCsv((Bank) bankBox.getValue(), bankFile)
                    .subscribeOn(Schedulers.io())
                    .subscribe(transaction -> {
                        transactionHistory.addTransaction(transaction);
                        transactionRepository.save(transaction);
                    });
        }
    }

    @Autowired
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setImporterService(ImporterService importerService) {
        this.importerService = importerService;
    }
}

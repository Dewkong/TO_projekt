package com.objectzilla.controller;

import com.objectzilla.model.Bank;
import com.objectzilla.model.Transaction;
import com.objectzilla.model.TransactionCategory;
import com.objectzilla.model.TransactionHistory;
import com.objectzilla.persistence.repository.TransactionRepository;
import com.objectzilla.service.ImporterService;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<Transaction, TransactionCategory> categoryColumn;

    @FXML
    private ChoiceBox<Object> bankBox;

    @FXML
    private Button openButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button editButton;

    private TransactionHistory transactionHistory;

    @FXML
    private void initialize() {
        transactionsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        operationColumn.setCellValueFactory(dataValue -> dataValue.getValue().operationDateProperty());
        bookingColumn.setCellValueFactory(dataValue -> dataValue.getValue().bookingDateProperty());
        titleColumn.setCellValueFactory(dataValue -> dataValue.getValue().titleProperty());
        nameColumn.setCellValueFactory(dataValue -> dataValue.getValue().transactioneeNameProperty());
        accountNumberColumn.setCellValueFactory(dataValue -> dataValue.getValue().transactioneeAccountNumberProperty());
        amountColumn.setCellValueFactory(dataValue -> dataValue.getValue().amountProperty());
        balanceColumn.setCellValueFactory(dataValue -> dataValue.getValue().balanceProperty());
        categoryColumn.setCellValueFactory(dataValue -> dataValue.getValue().transactionCategoryProperty());

        openButton.disableProperty().bind(bankBox.valueProperty().isNull());
        editButton.disableProperty().bind(Bindings.isEmpty(transactionsTable.getSelectionModel().getSelectedItems()));
        bankBox.getItems().setAll(FXCollections.observableArrayList(Bank.values()));

        setTransactionHistory(new TransactionHistory());
        for (Transaction transaction : transactionRepository.findAll()) {
            transactionHistory.addTransaction(transaction);
        }
    }


    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
        transactionsTable.setItems(transactionHistory.getTransactions());
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
                    .subscribe(transaction -> transactionHistory.addTransaction(transactionRepository.save(transaction)));
        }
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        transactionRepository.deleteAll();
        transactionHistory.getTransactions().clear();
    }

    @FXML
    private void handleEditAction(ActionEvent event){
        Transaction transaction = transactionsTable.getSelectionModel().getSelectedItem();
        if (transaction != null){
            appController.showEditDialog(transaction);
            transactionRepository.save(transaction);
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

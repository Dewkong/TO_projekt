package com.objectzilla.presenter;

import com.objectzilla.model.Transaction;
import com.objectzilla.model.TransactionCategory;
import com.objectzilla.util.MoneyParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionEditDialogPresenter {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Transaction transaction;
    private Stage dialogStage;

    @FXML
    private ComboBox<TransactionCategory> categoryComboBox;

    @FXML
    private TextField operationTextField;

    @FXML
    private TextField bookingTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField accountNumberTextField;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField balanceTextField;

    public void setData(Transaction transaction){
        this.transaction = transaction;
        updateControls();
    }

    public void setDialogStage(Stage stage){
        this.dialogStage = stage;
    }

    private void updateModel(){
        transaction.setOperationDate(LocalDate.parse(operationTextField.getText(), FORMATTER));
        transaction.setBookingDate(LocalDate.parse(bookingTextField.getText(), FORMATTER));
        transaction.setTitle(titleTextField.getText());
        transaction.setTransactioneeName(nameTextField.getText());
        transaction.setTransactioneeAccountNumber(accountNumberTextField.getText());
        transaction.setAmount(MoneyParser.parseMoneyString(amountTextField.getText()));
        transaction.setBalance(MoneyParser.parseMoneyString(balanceTextField.getText()));
        transaction.setTransactionCategory(categoryComboBox.getSelectionModel().getSelectedItem());
    }

    private void updateControls(){
        operationTextField.setText(transaction.getOperationDate().format(FORMATTER));
        bookingTextField.setText(transaction.getBookingDate().format(FORMATTER));
        titleTextField.setText(transaction.getTitle());
        nameTextField.setText(transaction.getTransactioneeName());
        accountNumberTextField.setText(transaction.getTransactioneeAccountNumber());
        amountTextField.setText(transaction.getAmount().toString());
        balanceTextField.setText(transaction.getBalance().toString());
        categoryComboBox.getSelectionModel().select(transaction.getTransactionCategory());
    }

    @FXML
    public void handleOkAction(ActionEvent event){
        updateModel();
        dialogStage.close();
    }

    @FXML
    public void handleCancelAction(ActionEvent event){
        dialogStage.close();
    }

    @FXML
    public void initialize() {
        categoryComboBox.getItems().setAll(TransactionCategory.values());
    }

}

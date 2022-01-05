package com.objectzilla.presenter;

import com.objectzilla.controller.Controller;
import com.objectzilla.model.Category;
import com.objectzilla.model.Transaction;
import com.objectzilla.util.MoneyParser;
import com.objectzilla.util.ValidationError;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class TransactionEditDialogPresenter implements Controller {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd" );

    private Transaction transaction;
    private Stage dialogStage;

    @FXML
    private DatePicker operationTextField;

    @FXML
    private DatePicker bookingTextField;

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

    @FXML
    public ChoiceBox<Category> categoryBox;

    public void setData(Transaction transaction) {
        this.transaction = transaction;
        updateControls();

        categoryBox.getItems().setAll(FXCollections.observableArrayList(Category.values()));
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    private void updateModel() {
        transaction.setOperationDate(operationTextField.getValue());
        transaction.setBookingDate(bookingTextField.getValue());
        transaction.setTitle(titleTextField.getText());
        transaction.setTransactioneeName(nameTextField.getText());
        transaction.setTransactioneeAccountNumber(accountNumberTextField.getText());
        transaction.setAmount(MoneyParser.parseMoneyString(amountTextField.getText()));
        transaction.setBalance(MoneyParser.parseMoneyString(balanceTextField.getText()));
        transaction.setCategory(categoryBox.getValue());
    }

    private void validateEdit() throws ValidationError {
        if (operationTextField.getValue() == null || bookingTextField.getValue() == null) {
            throw new ValidationError("Operation and booking date are required");
        }
        String accountNumber = accountNumberTextField.getText();
        if (accountNumber != null && !accountNumber.equals("") && !accountNumber.matches("\\d{26}")){
            throw new ValidationError("Invalid account number format");
        }
        try{
            MoneyParser.parseMoneyString(amountTextField.getText());
            MoneyParser.parseMoneyString(balanceTextField.getText());
        } catch (NumberFormatException e){
            throw new ValidationError("Invalid amount or balance format");
        }
    }

    private void updateControls() {
        operationTextField.setValue(transaction.getOperationDate());
        bookingTextField.setValue(transaction.getBookingDate());
        titleTextField.setText(transaction.getTitle());
        nameTextField.setText(transaction.getTransactioneeName());
        accountNumberTextField.setText(transaction.getTransactioneeAccountNumber());
        amountTextField.setText(transaction.getAmount().toString());
        balanceTextField.setText(transaction.getBalance().toString());
        categoryBox.setValue(transaction.getCategory());
    }


    @FXML
    public void handleOkAction(ActionEvent event) {
        try {
            validateEdit();
            updateModel();
            dialogStage.close();
        } catch (ValidationError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Please check your input");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleCancelAction(ActionEvent event) {
        dialogStage.close();
    }

}

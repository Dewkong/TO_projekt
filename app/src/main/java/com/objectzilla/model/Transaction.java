package com.objectzilla.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    private ObjectProperty<BigDecimal> amountProperty;
    private ObjectProperty<BigDecimal> balanceProperty;
    private ObjectProperty<LocalDate> operationDateProperty;
    private ObjectProperty<LocalDate> bookingDateProperty;
    private StringProperty titleProperty;
    private StringProperty transactioneeNameProperty; // nadawca/odbiorca
    private StringProperty transactioneeAccountNumberProperty;
    private ObjectProperty<TransactionCategory> category;
    private Long id;

    public Transaction() {
        this(BigDecimal.ZERO, BigDecimal.ZERO, null, null, "", "", "", TransactionCategory.NONE);
    }

    public Transaction(BigDecimal amount, BigDecimal balance, LocalDate operationDate, LocalDate bookingDate, String title, String transactioneeName, String transactioneeAccountNumber, TransactionCategory category) {
        this.amountProperty = new SimpleObjectProperty<>(amount);
        this.balanceProperty = new SimpleObjectProperty<>(balance);
        this.operationDateProperty = new SimpleObjectProperty<>(operationDate);
        this.bookingDateProperty = new SimpleObjectProperty<>(bookingDate);
        this.titleProperty = new SimpleStringProperty(title);
        this.transactioneeNameProperty = new SimpleStringProperty(transactioneeName);
        this.transactioneeAccountNumberProperty = new SimpleStringProperty(transactioneeAccountNumber);
        this.category = new SimpleObjectProperty<>(category);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amountProperty.getValue();
    }

    public void setAmount(BigDecimal amount) {
        this.amountProperty.setValue(amount);
    }

    public BigDecimal getBalance() {
        return balanceProperty.getValue();
    }

    public void setBalance(BigDecimal balance) {
        this.balanceProperty.setValue(balance);
    }

    public LocalDate getOperationDate() {
        return operationDateProperty.getValue();
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDateProperty.setValue(operationDate);
    }

    public LocalDate getBookingDate() {
        return bookingDateProperty.getValue();
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDateProperty.setValue(bookingDate);
    }

    public String getTitle() {
        return titleProperty.getValue();
    }

    public void setTitle(String title) {
        this.titleProperty.setValue(title);
    }

    public String getTransactioneeName() {
        return transactioneeNameProperty.getValue();
    }

    public void setTransactioneeName(String transactioneeName) {
        this.transactioneeNameProperty.setValue(transactioneeName);
    }

    public String getTransactioneeAccountNumber() {
        return transactioneeAccountNumberProperty.getValue();
    }

    public void setTransactioneeAccountNumber(String transactioneeAccountNumber) {
        this.transactioneeAccountNumberProperty.setValue(transactioneeAccountNumber);
    }

    public TransactionCategory getTransactionCategory() {
        return category.getValue();
    }

    public void setTransactionCategory(TransactionCategory category) {
        this.category.setValue(category);
    }

    public ObjectProperty<BigDecimal> amountProperty() {
        return amountProperty;
    }

    public ObjectProperty<BigDecimal> balanceProperty() {
        return balanceProperty;
    }

    public ObjectProperty<LocalDate> operationDateProperty() {
        return operationDateProperty;
    }

    public ObjectProperty<LocalDate> bookingDateProperty() {
        return bookingDateProperty;
    }

    public StringProperty titleProperty() {
        return titleProperty;
    }

    public StringProperty transactioneeNameProperty() {
        return transactioneeNameProperty;
    }

    public StringProperty transactioneeAccountNumberProperty() {
        return transactioneeAccountNumberProperty;
    }

    public ObjectProperty<TransactionCategory> transactionCategoryProperty() {
        return category;
    }

    public static class Builder {

        private BigDecimal amount = BigDecimal.ZERO;
        private BigDecimal balance = BigDecimal.ZERO;
        private LocalDate operationDate = null;
        private LocalDate bookingDate = null;
        private String title = "";
        private String transactioneeName = ""; // nadawca/odbiorca
        private String transactioneeAccountNumber = "";
        private TransactionCategory category;

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder operationDate(LocalDate operationDate) {
            this.operationDate = operationDate;
            return this;
        }

        public Builder bookingDate(LocalDate bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder transactioneeName(String transactioneeName) {
            this.transactioneeName = transactioneeName;
            return this;
        }

        public Builder transactioneeAccountNumber(String transactioneeAccountNumber) {
            this.transactioneeAccountNumber = transactioneeAccountNumber;
            return this;
        }

        public void category(TransactionCategory category) {
            this.category = category;
        }

        public Transaction build() {
            return new Transaction(amount, balance, operationDate, bookingDate, title, transactioneeName, transactioneeAccountNumber, category);
        }
    }
}

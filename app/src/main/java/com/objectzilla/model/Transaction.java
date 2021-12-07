package com.objectzilla.model;

import javafx.beans.property.*;

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
    private Long id;

    public Transaction() {

    }

    public Transaction(BigDecimal bigDecimal, BigDecimal balance, LocalDate operationDate, LocalDate bookingDate, String title, String transactioneeName, String transactioneeAccountNumber) {
        this.amountProperty = new SimpleObjectProperty<BigDecimal>(bigDecimal);
        this.balanceProperty = new SimpleObjectProperty<BigDecimal>(balance);
        this.operationDateProperty = new SimpleObjectProperty<LocalDate>(operationDate);
        this.bookingDateProperty = new SimpleObjectProperty<LocalDate>(bookingDate);
        this.titleProperty = new SimpleStringProperty(title);
        this.transactioneeNameProperty = new SimpleStringProperty(transactioneeName);
        this.transactioneeAccountNumberProperty = new SimpleStringProperty(transactioneeAccountNumber);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public BigDecimal amount() {
        return amountProperty.getValue();
    }

    public void setAmount(BigDecimal amount) {
        this.amountProperty.setValue(amount);
    }

    public BigDecimal balance() {
        return balanceProperty.getValue();
    }

    public void setBalance(BigDecimal balance) {
        this.balanceProperty.setValue(balance);
    }

    public LocalDate operationDate() {
        return operationDateProperty.getValue();
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDateProperty.setValue(operationDate);
    }

    public LocalDate bookingDate() {
        return bookingDateProperty.getValue();
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDateProperty.setValue(bookingDate);
    }

    public String title() {
        return titleProperty.getValue();
    }

    public void setTitle(String title) {
        this.titleProperty.setValue(title);
    }

    public String transactioneeName() {
        return transactioneeNameProperty.getValue();
    }

    public void setTransactioneeName(String transactioneeName) {
        this.transactioneeNameProperty.setValue(transactioneeName);
    }

    public String transactioneeAccountNumber() {
        return transactioneeAccountNumberProperty.getValue();
    }

    public void setTransactioneeAccountNumber(String transactioneeAccountNumber) {
        this.transactioneeAccountNumberProperty.setValue(transactioneeAccountNumber);
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

    public static class Builder {

        private BigDecimal amount = BigDecimal.ZERO;
        private BigDecimal balance = BigDecimal.ZERO;
        private LocalDate operationDate = null;
        private LocalDate bookingDate = null;
        private String title = "";
        private String transactioneeName = ""; // nadawca/odbiorca
        private String transactioneeAccountNumber = "";

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

        public Transaction build() {
            return new Transaction(amount, balance, operationDate, bookingDate, title, transactioneeName, transactioneeAccountNumber);
        }
    }
}

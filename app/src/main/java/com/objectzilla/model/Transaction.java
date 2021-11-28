package com.objectzilla.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private final ObjectProperty<BigDecimal> amount;
    private final ObjectProperty<BigDecimal> balance;
    private final ObjectProperty<LocalDate> operationDate;
    private final ObjectProperty<LocalDate> bookingDate;
    private final StringProperty title;
    private final StringProperty transactioneeName; // nadawca/odbiorca
    private final StringProperty transactioneeAccountNumber;

    public Transaction(BigDecimal bigDecimal, BigDecimal balance, LocalDate operationDate, LocalDate bookingDate, String title, String transactioneeName, String transactioneeAccountNumber) {
        this.amount = new SimpleObjectProperty<>(bigDecimal);
        this.balance = new SimpleObjectProperty<>(balance);
        this.operationDate = new SimpleObjectProperty<>(operationDate);
        this.bookingDate = new SimpleObjectProperty<>(bookingDate);
        this.title = new SimpleStringProperty(title);
        this.transactioneeName = new SimpleStringProperty(transactioneeName);
        this.transactioneeAccountNumber = new SimpleStringProperty(transactioneeAccountNumber);
    }

    public BigDecimal getAmount() {
        return amount.getValue();
    }

    public BigDecimal getBalance() {
        return balance.getValue();
    }

    public LocalDate getOperationDate() {
        return operationDate.getValue();
    }

    public LocalDate getBookingDate() {
        return bookingDate.getValue();
    }

    public String getTitle() {
        return title.getValue();
    }

    public String getTransactioneeName() {
        return transactioneeName.getValue();
    }

    public String getTransactioneeAccountNumber() {
        return transactioneeAccountNumber.getValue();
    }

    public ObjectProperty<BigDecimal> amountProperty() {
        return amount;
    }

    public ObjectProperty<BigDecimal> balanceProperty() {
        return balance;
    }

    public ObjectProperty<LocalDate> operationDateProperty() {
        return operationDate;
    }

    public ObjectProperty<LocalDate> bookingDateProperty() {
        return bookingDate;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty transactioneeNameProperty() {
        return transactioneeName;
    }

    public StringProperty transactioneeAccountNumberProperty() {
        return transactioneeAccountNumber;
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

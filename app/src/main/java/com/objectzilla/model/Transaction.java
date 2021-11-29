package com.objectzilla.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private final BigDecimal amount;
    private final BigDecimal balance;
    private final LocalDate operationDate;
    private final LocalDate bookingDate;
    private final String title;
    private final String transactioneeName; // nadawca/odbiorca
    private final String transactioneeAccountNumber;

    public Transaction(BigDecimal bigDecimal, BigDecimal balance, LocalDate operationDate, LocalDate bookingDate, String title, String transactioneeName, String transactioneeAccountNumber) {
        this.amount = bigDecimal;
        this.balance = balance;
        this.operationDate = operationDate;
        this.bookingDate = bookingDate;
        this.title = title;
        this.transactioneeName = transactioneeName;
        this.transactioneeAccountNumber = transactioneeAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public String getTitle() {
        return title;
    }

    public String getTransactioneeName() {
        return transactioneeName;
    }

    public String getTransactioneeAccountNumber() {
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

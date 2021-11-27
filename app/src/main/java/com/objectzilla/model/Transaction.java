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

    private Transaction(BigDecimal bigDecimal, BigDecimal balance, LocalDate operationDate, LocalDate bookingDate, String title, String transactioneeName, String transactioneeAccountNumber) {
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

    public static class TransactionBuilder {

        private BigDecimal amount = BigDecimal.ZERO;
        private BigDecimal balance = BigDecimal.ZERO;
        private LocalDate operationDate = null;
        private LocalDate bookingDate = null;
        private String title = "";
        private String transactioneeName = ""; // nadawca/odbiorca
        private String transactioneeAccountNumber = "";

        TransactionBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        TransactionBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        TransactionBuilder operationDate(LocalDate operationDate) {
            this.operationDate = operationDate;
            return this;
        }

        TransactionBuilder bookingDate(LocalDate bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        TransactionBuilder title(String title) {
            this.title = title;
            return this;
        }

        TransactionBuilder transactioneeName(String transactioneeName) {
            this.transactioneeName = transactioneeName;
            return this;
        }

        TransactionBuilder transactioneeAccountNumber(String transactioneeAccountNumber) {
            this.transactioneeAccountNumber = transactioneeAccountNumber;
            return this;
        }

        Transaction build() {
            return new Transaction(amount, balance, operationDate, bookingDate, title, transactioneeName, transactioneeAccountNumber);
        }
    }
}

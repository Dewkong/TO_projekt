package com.objectzilla.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    private BigDecimal amount;
    private BigDecimal balance;
    private LocalDate operationDate;
    private LocalDate bookingDate;
    private String title;
    private String transactioneeName; // nadawca/odbiorca
    private String transactioneeAccountNumber;
    private Long id;

    public Transaction() {

    }

    public Transaction(BigDecimal bigDecimal, BigDecimal balance, LocalDate operationDate, LocalDate bookingDate, String title, String transactioneeName, String transactioneeAccountNumber) {
        this.amount = bigDecimal;
        this.balance = balance;
        this.operationDate = operationDate;
        this.bookingDate = bookingDate;
        this.title = title;
        this.transactioneeName = transactioneeName;
        this.transactioneeAccountNumber = transactioneeAccountNumber;
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
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTransactioneeName() {
        return transactioneeName;
    }

    public void setTransactioneeName(String transactioneeName) {
        this.transactioneeName = transactioneeName;
    }

    public String getTransactioneeAccountNumber() {
        return transactioneeAccountNumber;
    }

    public void setTransactioneeAccountNumber(String transactioneeAccountNumber) {
        this.transactioneeAccountNumber = transactioneeAccountNumber;
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

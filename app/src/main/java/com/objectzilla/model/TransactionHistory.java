package com.objectzilla.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {

    private final ObservableList<Transaction> transactions;

    public TransactionHistory() {
        transactions = FXCollections.observableList(new ArrayList<>());
    }

    public TransactionHistory(List<Transaction> transactionList) {
        transactions = FXCollections.observableList(transactionList);
    }

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}

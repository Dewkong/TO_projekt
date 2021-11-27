package com.objectzilla.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TransactionHistory {

    private final ObservableList<Transaction> transactions = FXCollections.observableList(new ArrayList<>());

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}

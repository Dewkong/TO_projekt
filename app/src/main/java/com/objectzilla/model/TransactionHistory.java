package com.objectzilla.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionHistory {

    private final ObservableList<Transaction> transactions = FXCollections.emptyObservableList();

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }
}

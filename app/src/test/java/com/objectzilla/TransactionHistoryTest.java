package com.objectzilla;

import com.objectzilla.model.Transaction;
import com.objectzilla.model.TransactionHistory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionHistoryTest {

    private TransactionHistory transactionHistory;

    @BeforeEach
    void setUp() {
        transactionHistory = new TransactionHistory();
    }

    @Test
    void rxAdd() {
        var observable = Observable.interval(1, TimeUnit.MICROSECONDS);
        //noinspection ResultOfMethodCallIgnored
        observable
                .subscribeOn(Schedulers.io())
                .take(100)
                .doOnComplete(() -> assertEquals(100, transactionHistory.getTransactions().size()))
                .subscribe(i -> transactionHistory.addTransaction(new Transaction.Builder().build()));
    }
}

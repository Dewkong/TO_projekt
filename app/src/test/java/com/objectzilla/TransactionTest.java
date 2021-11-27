package com.objectzilla;

import com.objectzilla.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest {
    @Test
    public void createTransaction() {
        Transaction.TransactionBuilder transactionBuilder = new Transaction.TransactionBuilder();
        transactionBuilder.amount(BigDecimal.valueOf(100));
        transactionBuilder.balance(BigDecimal.valueOf(200));
        transactionBuilder.title("Tytul");
        var transaction = transactionBuilder.build();
        assertNotNull(transaction);
        assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
        assertEquals(BigDecimal.valueOf(200), transaction.getBalance());
        assertEquals("Tytul", transaction.getTitle());
    }
}

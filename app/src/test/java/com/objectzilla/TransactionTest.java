package com.objectzilla;

import com.objectzilla.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest {
    @Test
    public void createTransaction() {
        var transaction = new Transaction.Builder()
                .amount(BigDecimal.valueOf(100))
                .balance(BigDecimal.valueOf(200))
                .title("Tytul")
                .build();
        assertNotNull(transaction);
        assertEquals(BigDecimal.valueOf(100), transaction.amount());
        assertEquals(BigDecimal.valueOf(200), transaction.balance());
        assertEquals("Tytul", transaction.title());
    }
}

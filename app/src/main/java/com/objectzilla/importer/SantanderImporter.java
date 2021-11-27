package com.objectzilla.importer;

import com.objectzilla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SantanderImporter implements Importer{
    private final int headerLength = 0;

    @Override
    public Transaction importLine(List<String> line) {
        Transaction.TransactionBuilder transactionBuilder = new Transaction.TransactionBuilder();
        transactionBuilder.operationDate(LocalDate.parse(line.get(1)));
        transactionBuilder.bookingDate(LocalDate.parse(line.get(0)));
        transactionBuilder.title(line.get(2));
        transactionBuilder.transactioneeName(line.get(3));
        transactionBuilder.transactioneeAccountNumber(line.get(4));
        transactionBuilder.amount(new BigDecimal(line.get(5)));
        transactionBuilder.balance(new BigDecimal(line.get(6)));

        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return headerLength;
    }
}

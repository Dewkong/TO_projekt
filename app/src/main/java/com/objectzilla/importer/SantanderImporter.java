package com.objectzilla.importer;

import com.objectzilla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SantanderImporter implements Importer {
    private final int headerLength = 0;

    @Override
    public Transaction importLine(List<String> line) {
        Transaction.TransactionBuilder transactionBuilder = new Transaction.TransactionBuilder()
                .operationDate(LocalDate.parse(line.get(1)))
                .bookingDate(LocalDate.parse(line.get(0)))
                .title(line.get(2))
                .transactioneeName(line.get(3))
                .transactioneeAccountNumber(line.get(4))
                .amount(new BigDecimal(line.get(5)))
                .balance(new BigDecimal(line.get(6)));

        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return headerLength;
    }
}

package com.objectzilla.importer;

import com.objectzilla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MbankImporter implements Importer {
    private final int headerLength = 38;

    @Override
    public Transaction importLine(List<String> line) {
        Transaction.TransactionBuilder transactionBuilder = new Transaction.TransactionBuilder()
                .operationDate(LocalDate.parse(line.get(0)))
                .bookingDate(LocalDate.parse(line.get(1)))
                .title(line.get(3))
                .transactioneeName(line.get(4))
                .transactioneeAccountNumber(line.get(5))
                .amount(new BigDecimal(line.get(6)))
                .balance(new BigDecimal(line.get(7)));

        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return headerLength;
    }
}

package com.objectzilla.importer;

import com.objectzilla.model.Transaction;
import io.reactivex.rxjava3.core.Observable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MbankImporter implements Importer{
    private final int headerLength = 38;

    @Override
    public Transaction importLine(List<String> line) {
        Transaction.TransactionBuilder transactionBuilder = new Transaction.TransactionBuilder();
        transactionBuilder.operationDate(LocalDate.parse(line.get(0)));
        transactionBuilder.bookingDate(LocalDate.parse(line.get(1)));
        transactionBuilder.title(line.get(3));
        transactionBuilder.transactioneeName(line.get(4));
        transactionBuilder.transactioneeAccountNumber(line.get(5));
        transactionBuilder.amount(new BigDecimal(line.get(6)));
        transactionBuilder.balance(new BigDecimal(line.get(7)));

        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return headerLength;
    }
}

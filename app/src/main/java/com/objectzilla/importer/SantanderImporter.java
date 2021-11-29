package com.objectzilla.importer;

import com.objectzilla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SantanderImporter implements Importer {
    private final int headerLength = 1;

    @Override
    public Transaction importLine(List<String> line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        Transaction.Builder transactionBuilder = new Transaction.Builder()
                .operationDate(LocalDate.parse(line.get(1), formatter))
                .bookingDate(LocalDate.parse(line.get(0), formatter))
                .title(line.get(2))
                .transactioneeName(line.get(3))
                .transactioneeAccountNumber(line.get(4))
                .amount(new BigDecimal(line.get(5).replace(" ", "").replace(',', '.')))
                .balance(new BigDecimal(line.get(6).replace(" ", "").replace(',', '.')));


        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return headerLength;
    }
}

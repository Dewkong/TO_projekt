package com.objectzilla.importer;

import com.objectzilla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SantanderImporter implements Importer {
    private final static int HEADER_LENGTH = 1;
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    @Override
    public Transaction importLine(List<String> line) {
        Transaction.Builder transactionBuilder = new Transaction.Builder()
                .operationDate(LocalDate.parse(line.get(1), FORMATTER))
                .bookingDate(LocalDate.parse(line.get(0), FORMATTER))
                .title(line.get(2))
                .transactioneeName(line.get(3))
                .transactioneeAccountNumber(line.get(4))
                .amount(new BigDecimal(line.get(5).replace(" ", "").replace(',', '.')))
                .balance(new BigDecimal(line.get(6).replace(" ", "").replace(',', '.')));


        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return HEADER_LENGTH;
    }
}

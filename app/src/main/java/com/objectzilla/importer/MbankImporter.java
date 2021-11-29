package com.objectzilla.importer;

import com.objectzilla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MbankImporter implements Importer {
    private final static int headerLength = 38;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    @Override
    public Transaction importLine(List<String> line) {
        System.out.println(line);
        Transaction.Builder transactionBuilder = new Transaction.Builder()
                .operationDate(LocalDate.parse(line.get(0), formatter))
                .bookingDate(LocalDate.parse(line.get(1), formatter))
                .title(line.get(3))
                .transactioneeName(line.get(4))
                .transactioneeAccountNumber(line.get(5))
                .amount(new BigDecimal(line.get(6).replace(" ", "").replace(',', '.')))
                .balance(new BigDecimal(line.get(7).replace(" ", "").replace(',', '.')));


        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return headerLength;
    }
}

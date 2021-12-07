package com.objectzilla.importer;

import com.objectzilla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MbankImporter implements Importer {
    private final static int HEADER_LENGTH = 38;
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    private final static int OPERATION_DATE_INDEX = 0;
    private final static int BOOKING_DATE_INDEX = 1;
    private final static int TITLE_INDEX = 3;
    private final static int TRANSACTIONEE_NAME_INDEX = 4;
    private final static int TRANSACTIONEE_ACCOUNT_NUMBER_INDEX = 5;
    private final static int AMOUNT_INDEX = 6;
    private final static int BALANCE_INDEX = 7;

    @Override
    public Transaction importLine(List<String> line) {
        System.out.println(line);
        Transaction.Builder transactionBuilder = new Transaction.Builder()
                .operationDate(LocalDate.parse(line.get(OPERATION_DATE_INDEX), FORMATTER))
                .bookingDate(LocalDate.parse(line.get(BOOKING_DATE_INDEX), FORMATTER))
                .title(line.get(TITLE_INDEX))
                .transactioneeName(line.get(TRANSACTIONEE_NAME_INDEX))
                .transactioneeAccountNumber(line.get(TRANSACTIONEE_ACCOUNT_NUMBER_INDEX))
                .amount(new BigDecimal(line.get(AMOUNT_INDEX).replace(" ", "").replace(',', '.')))
                .balance(new BigDecimal(line.get(BALANCE_INDEX).replace(" ", "").replace(',', '.')));
        return transactionBuilder.build();
    }

    @Override
    public int getHeaderLength() {
        return HEADER_LENGTH;
    }
}

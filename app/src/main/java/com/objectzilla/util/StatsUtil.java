package com.objectzilla.util;

import com.objectzilla.model.Category;
import com.objectzilla.model.DailyTransactionData;
import com.objectzilla.model.Transaction;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatsUtil {
    public static DailyTransactionData createDailyTransactionData(LocalDate startDate, LocalDate endDate,
                                                                  Collection<Transaction> transactions) {
        if (startDate != null && endDate != null && !startDate.isAfter(endDate)) {
            DailyTransactionData data = new DailyTransactionData(startDate.datesUntil(endDate.plusDays(1))
                    .collect(Collectors.toList()));
            for (Transaction transaction : transactions) {
                LocalDate date = transaction.getBookingDate();
                if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                    data.setValue(date, transaction.getAmount().doubleValue());
                }
            }
            return data;
        }
        return new DailyTransactionData(Collections.emptyList());
    }

    public static Map<Category, Double> calculateExpensesByCategory(Collection<Transaction> transactions,
                                                                    LocalDate startDate, LocalDate endDate) {
        Map<Category, Double> mapping = new HashMap<>();
        for (Transaction transaction : transactions) {
            double value = transaction.getAmount().doubleValue();
            LocalDate date = transaction.getBookingDate();
            if (value < 0.0 && transaction.getCategory() != null
                    && (startDate == null || !date.isBefore(startDate))
                    && (endDate == null || !date.isAfter(endDate))) {
                mapping.compute(transaction.getCategory(),
                        (key, oldValue) -> oldValue == null ? -value : oldValue - value);
            }
        }
        return mapping;
    }
}

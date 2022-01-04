package com.objectzilla.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyTransactionData {
    private final List<LocalDate> dates;
    private final Map<LocalDate, Double> valueMapping = new HashMap<>();

    public DailyTransactionData(List<LocalDate> dates) {
        this.dates = List.copyOf(dates);
    }

    public void setValue(LocalDate date, Double value) {
        if (dates.size() == 0 || date.isBefore(getStartDate()) || date.isAfter(date)) {
            throw new IllegalArgumentException("Date is out of provided range");
        }
        valueMapping.compute(date, (key, val) -> val == null ? value : val + value);
    }

    public Double getValue(LocalDate date) {
        if (dates.size() == 0 || date.isBefore(getStartDate()) || date.isAfter(getEndDate())) {
            return null;
        }
        return valueMapping.getOrDefault(date, 0.0);
    }

    public LocalDate getStartDate() {
        if (dates.size() == 0) {
            return null;
        }
        return dates.get(0);
    }

    public LocalDate getEndDate() {
        if (dates.size() == 0) {
            return null;
        }
        return dates.get(dates.size() - 1);
    }

    public List<LocalDate> getDates() {
        return dates;
    }
}

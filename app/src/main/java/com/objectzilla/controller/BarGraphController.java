package com.objectzilla.controller;

import com.objectzilla.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BarGraphController implements Controller {
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart<String, Double> chart;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    private final ObservableList<String> dateLabels = FXCollections.observableArrayList();
    private final HashMap<String, Double> values = new HashMap<>();

    @FXML
    private void initialize() {
        startDate.valueProperty().addListener((observable, oldValue, newValue)
                -> updateList(newValue, endDate.getValue()));
        endDate.valueProperty().addListener((observable, oldValue, newValue)
                -> updateList(startDate.getValue(), newValue));

        xAxis.setCategories(dateLabels);
    }

    public void updateData(Collection<Transaction> transactions) {
        values.clear();
        for (Transaction transaction : transactions) {
            String key = transaction.getBookingDate().toString();
            double currentValue = values.getOrDefault(key, 0.0);
            values.put(key, currentValue + transaction.getAmount().doubleValue());
        }
        updateYAxis();
    }

    private void updateYAxis() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (String date : dateLabels) {
            series.getData().add(new XYChart.Data<>(date, values.getOrDefault(date, 0.0)));
        }
        chart.getData().clear();
        chart.getData().add(series);
    }

    private void updateList(LocalDate start, LocalDate end) {
        dateLabels.clear();
        if (start != null && end != null) {
            Stream<LocalDate> dates = start.datesUntil(end.plusDays(1));
            dateLabels.addAll(dates.map(LocalDate::toString).collect(Collectors.toList()));
        }
        updateYAxis();
    }
}

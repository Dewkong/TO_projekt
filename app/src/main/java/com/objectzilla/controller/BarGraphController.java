package com.objectzilla.controller;

import com.objectzilla.model.DailyTransactionData;
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
import java.util.Collections;

import static com.objectzilla.util.StatsUtil.createDailyTransactionData;

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
    private Collection<Transaction> transactions = Collections.emptyList();

    @FXML
    private void initialize() {
        startDate.valueProperty().addListener((observable, oldValue, newValue)
                -> updateRange(newValue, endDate.getValue()));
        endDate.valueProperty().addListener((observable, oldValue, newValue)
                -> updateRange(startDate.getValue(), newValue));

        xAxis.setCategories(dateLabels);
    }

    public void updateData(Collection<Transaction> transactions) {
        setData(createDailyTransactionData(startDate.getValue(), endDate.getValue(), transactions));
        this.transactions = transactions;
    }

    private void setData(DailyTransactionData data) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        dateLabels.clear();
        for (LocalDate date : data.getDates()) {
            dateLabels.add(date.toString());
            series.getData().add(new XYChart.Data<>(date.toString(), data.getValue(date)));
        }
        chart.getData().clear();
        chart.getData().add(series);
    }

    private void updateRange(LocalDate start, LocalDate end) {
        setData(createDailyTransactionData(start, end, transactions));
    }
}

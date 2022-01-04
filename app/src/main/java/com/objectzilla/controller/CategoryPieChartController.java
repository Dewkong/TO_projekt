package com.objectzilla.controller;

import com.objectzilla.model.Category;
import com.objectzilla.model.Transaction;
import com.objectzilla.util.StatsUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CategoryPieChartController implements Controller {
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private PieChart pieChart;

    @FXML
    private Label valueLabel;

    private final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private Collection<Transaction> transactions = Collections.emptyList();

    @FXML
    private void initialize() {
        pieChart.setData(pieChartData);

        startDate.valueProperty().addListener((observable, oldValue, newValue)
                -> updateRange(newValue, endDate.getValue()));
        endDate.valueProperty().addListener((observable, oldValue, newValue)
                -> updateRange(startDate.getValue(), newValue));
    }

    private void setData(Map<Category, Double> data) {
        pieChartData.clear();
        for (Category category : data.keySet()) {
            pieChartData.add(new PieChart.Data(category.name(), data.get(category)));
        }
        updateValueLabelBehavior();
    }

    private void updateValueLabelBehavior() {
        for (final PieChart.Data data : pieChartData) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                BigDecimal value = BigDecimal.valueOf(data.getPieValue()).setScale(2, RoundingMode.HALF_UP);
                valueLabel.setText(value.toString());
                valueLabel.setVisible(true);
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                valueLabel.setVisible(false);
                valueLabel.setText("");
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
                valueLabel.setTranslateX(event.getX());
                valueLabel.setTranslateY(event.getY() - 30);
            });
        }
    }

    public void updateData(Collection<Transaction> transactions) {
        this.transactions = transactions;
        setData(StatsUtil.calculateExpensesByCategory(transactions, startDate.getValue(), endDate.getValue()));
    }

    public void updateRange(LocalDate start, LocalDate end) {
        setData(StatsUtil.calculateExpensesByCategory(transactions, start, end));
    }
}

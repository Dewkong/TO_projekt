package com.objectzilla.controller;

import com.objectzilla.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CategoryPieChartController implements Controller {
    @FXML
    private PieChart pieChart;

    private static final String[] colors = new String[]{
            "maroon",
            "red",
            "purple",
            "fuchsia",
            "green",
            "lime",
            "olive",
            "yellow",
            "navy",
            "blue",
            "teal",
            "aqua"
    };

    @FXML
    private void initialize() {

    }

    public void updateData(Collection<Transaction> transactions) {
        pieChart.getData().clear();
        Map<String, Double> values = new HashMap<>();
        for (Transaction transaction : transactions) {
            String category = transaction.getCategory() != null ? transaction.getCategory().name() : null;
            if (category != null && transaction.getAmount().doubleValue() < 0.0) {
                double currentValue = values.getOrDefault(category, 0.0);
                values.put(category, currentValue + transaction.getAmount().doubleValue());
            }
        }

        for (String category : values.keySet()) {
            pieChart.getData().add(new PieChart.Data(category, values.get(category)));
        }

        updateColors();
    }

    private void updateColors() {
        int i = 0;
        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().setStyle("-fx-pie-color: " + colors[i % colors.length] + ";" );
            i++;
        }
    }
}

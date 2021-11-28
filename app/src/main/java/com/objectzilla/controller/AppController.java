package com.objectzilla.controller;

import com.objectzilla.model.Transaction;
import com.objectzilla.model.TransactionHistory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AppController {
    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AppController(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass()
                    .getResource("../../../view/TransactionHistoryPane.fxml"));
            BorderPane rootLayout = loader.load();

            TransactionHistoryController controller = loader.getController();
            controller.setAppController(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

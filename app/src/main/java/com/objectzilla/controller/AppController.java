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

            //MOCK VALUES
            //TO REMOVE
            Transaction t1 = new Transaction(BigDecimal.valueOf(23.0), BigDecimal.valueOf(50.0), LocalDate.now(), LocalDate.now(), "tytul1", "nazwa1", "numer1");
            Transaction t2 = new Transaction(BigDecimal.valueOf(43.0), BigDecimal.valueOf(70.0), LocalDate.now(), LocalDate.now(), "tytul2", "nazwa2", "numer2");
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.addTransaction(t1);
            transactionHistory.addTransaction(t2);
            controller.setTransactionHistory(transactionHistory);
            //end MOCK VALUES

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

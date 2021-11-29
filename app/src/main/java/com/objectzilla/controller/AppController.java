package com.objectzilla.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class AppController {
    private Stage primaryStage;
    private TransactionHistoryController transactionHistoryController;

    public void initRootLayout(Stage primaryStage) {
        try{
            Parent rootLayout = loadFxml("/view/TransactionHistoryPane.fxml", transactionHistoryController);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            this.primaryStage = primaryStage;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Parent loadFxml(String view, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        loader.setControllerFactory(param -> controller);
        loader.load();
        return loader.getRoot();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Autowired
    public void setTransactionHistoryController(TransactionHistoryController transactionHistoryController) {
        this.transactionHistoryController = transactionHistoryController;
    }
}

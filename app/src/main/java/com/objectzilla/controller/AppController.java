package com.objectzilla.controller;

import com.objectzilla.model.Transaction;
import com.objectzilla.presenter.TransactionEditDialogPresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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

    public void showEditDialog(Transaction transaction){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/TransactionEditDialog.fxml"));
            BorderPane page = loader.load();
            Scene scene = new Scene(page);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit transaction");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            TransactionEditDialogPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setData(transaction);

            dialogStage.showAndWait();
        } catch (IOException e) {
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

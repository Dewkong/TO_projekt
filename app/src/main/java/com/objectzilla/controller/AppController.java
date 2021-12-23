package com.objectzilla.controller;

import com.objectzilla.model.Transaction;
import com.objectzilla.presenter.TransactionEditDialogPresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;

public class AppController implements ApplicationContextAware {
    private Stage primaryStage;
    private TransactionEditDialogPresenter transactionEditDialogPresenter;
    private ApplicationContext context;
    private ControllerRepository controllerRepository;

    public void initRootLayout(Stage primaryStage) {
        try{
            Parent rootLayout = loadFxml("/view/MainDialog.fxml");
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
            Parent parent = loadFxml("/view/TransactionEditDialog.fxml");
            Scene scene = new Scene(parent);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit transaction");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            transactionEditDialogPresenter.setDialogStage(dialogStage);
            transactionEditDialogPresenter.setData(transaction);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Parent loadFxml(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        loader.setControllerFactory(controllerRepository::findController);
        loader.load();
        return loader.getRoot();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Autowired
    public void setTransactionEditDialogPresenter(TransactionEditDialogPresenter transactionEditDialogPresenter) {
        this.transactionEditDialogPresenter = transactionEditDialogPresenter;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Autowired
    public void setControllerRepository(ControllerRepository repository) {
        this.controllerRepository = repository;
    }
}

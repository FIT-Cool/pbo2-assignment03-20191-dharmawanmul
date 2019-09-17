package com.mulyadi;

import com.mulyadi.controller.CategoryManagementController;
import com.mulyadi.controller.MainFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainForm.fxml"));
            BorderPane root = loader.load();
            MainFormController mainFormController = loader.getController();
            mainFormController.setController(mainFormController); //this
            Stage mainStage = new Stage();
            mainStage.setTitle("PBO2 #3 Praktikum");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

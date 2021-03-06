package com.halzfaller.app;

/**
 * Created by rbu on 3/31/17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Halzfaller extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        URL url = getClass().getClassLoader().getResource("ui/login.fxml");
        Parent root = FXMLLoader.load(url);

        primaryStage.setTitle("Halzfaller");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void changeScene(String title, Scene scene){
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
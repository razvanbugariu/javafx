package com.halzfaller.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by rbu on 4/1/17.
 */
public class SecondSceneController {

    @FXML
    private Label entitiesLabel;

    @FXML
    private Button backToLoginSceneButton;

    public void initialize(){
        entitiesLabel.setText("Text");
        backToLoginSceneButton.setOnAction(event -> backToLogin());
    }

    private void backToLogin() {
        Pane myPane = null;
        try {
            myPane = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        } catch (IOException e) {
            System.out.println("FXML file for second screen not found!");
        }
        Halzfaller.changeScene("Login", new Scene(myPane, 300, 250));
    }

}

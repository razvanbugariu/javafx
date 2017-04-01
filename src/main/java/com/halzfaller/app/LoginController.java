package com.halzfaller.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.halzfaller.app.entities.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by rbu on 4/1/17.
 */
public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorsLabel;

    public void initialize(){
        loginButton.setOnAction(event -> login());
    }

    private void login(){
        Client client = Client.create();
        Gson gson = new Gson();
        String toJson = gson.toJson(new User(usernameTextField.getText(), passwordTextField.getText()));
        System.out.println(toJson);
        ClientResponse response = client.resource("https://mentors4me-api.herokuapp.com/api/sessions").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, toJson);
        System.out.println(response);
        changeScene();
    }

    private void changeScene(){
        Stage stage = new Stage();
        stage.setTitle("Second Scene");
        Pane myPane = null;
        try {
            myPane = FXMLLoader.load(getClass().getClassLoader().getResource("secondScene.fxml"));
        } catch (IOException e) {
            System.out.println("FXML file for second screen not found!");
        }
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }

}

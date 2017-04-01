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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                login();
            }
        });
    }

    private void login(){
        Client client = Client.create();
        WebResource resource = client.resource("https://mentors4me-api.herokuapp.com/api/sessions");
        Gson gson = new Gson();
        String toJson = gson.toJson(new User(usernameTextField.getText(), passwordTextField.getText()));
        System.out.println(toJson);
        ClientResponse response = resource.post(ClientResponse.class, toJson);
        System.out.println(response);
    }

}

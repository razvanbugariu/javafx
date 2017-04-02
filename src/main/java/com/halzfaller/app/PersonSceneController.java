package com.halzfaller.app;

import com.halzfaller.app.entities.Person;
import com.halzfaller.app.repository.PersonRepo;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PersonSceneController {

    private PersonRepo personRepo = new PersonRepo();
    private ObservableList<Person> people = FXCollections.observableArrayList();

    @FXML
    private TableView<Person> peopleTable;

    @FXML
    private TableColumn<Person, String> firstName;

    @FXML
    private TableColumn<Person, String> lastName;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private Button backToLoginSceneButton;

    public void initialize(){
        backToLoginSceneButton.setOnAction(event -> backToLogin());
        initTable();
    }

    private void initTable() {
        people.addAll(personRepo.findAll());
        peopleTable.setItems(people);

        firstName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getFirstName()));
        lastName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getLastName()));
    }

    private void backToLogin() {
        try {
            Pane myPane = FXMLLoader.load(getClass().getClassLoader().getResource("ui/login.fxml"));
            Halzfaller.changeScene("Login", new Scene(myPane, 400, 600));
        } catch (IOException e) {
            System.out.println("FXML file for second screen not found!");
        }
    }

    @FXML
    public void addPerson(ActionEvent actionEvent) {
        System.out.println("clicked");
        Person newPerson = new Person(firstNameInput.getText(), lastNameInput.getText());
        personRepo.save(newPerson);
        people.add(newPerson);
        firstNameInput.clear();
        lastNameInput.clear();
    }
}

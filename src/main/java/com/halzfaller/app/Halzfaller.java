package com.halzfaller.app;

/**
 * Created by rbu on 3/31/17.
 */

import com.halzfaller.app.database.DatabaseWrapper;
import com.halzfaller.app.entities.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

public class Halzfaller extends Application {

    public static void main(String[] args) {
        testDb();
        launch(args);
    }

    private static void testDb() {
        DatabaseWrapper wrapper = new DatabaseWrapper();
        Person person = new Person("Josh Long");
        wrapper.save(person);
        List<Person> people = wrapper.findAll("Select p from Person p", Person.class);
        System.out.println(people);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = null;
        URL url = getClass().getClassLoader().getResource("login.fxml");
        root = FXMLLoader.load(url);

        primaryStage.setTitle("Halzfaller");
        primaryStage.setScene(new Scene(root, 250, 300));
        primaryStage.show();
    }
}
package com.halzfaller.app;

/**
 * Created by rbu on 3/31/17.
 */

import com.halzfaller.app.repository.PersonRepo;
import com.halzfaller.app.entities.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

public class Halzfaller extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        testDb();
        launch(args);
    }

    private static void testDb() {
        PersonRepo personRepo = new PersonRepo();
        Person person = new Person("Josh Long");
        personRepo.save(person);
        List<Person> people = personRepo.findAll();
        System.out.println(people);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = null;
        URL url = getClass().getClassLoader().getResource("login.fxml");
        root = FXMLLoader.load(url);

        primaryStage.setTitle("Halzfaller");
        primaryStage.setScene(new Scene(root, 250, 300));
        primaryStage.show();
    }

    public static void changeScene(String title, Scene scene){
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
package com.example.computingclub;

import com.example.computingclub.userset.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public final class Util {
    public static void changeScreen(ActionEvent event, String fxml, String header) throws IOException {
        Parent nextScene = FXMLLoader.load(Objects.requireNonNull(Util.class.getResource(fxml)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(nextScene);
        stage.setTitle(header);
        stage.setScene(scene);
        stage.show();
    }

    public static void saveUser(User user, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        oos.close();
    }

    public static User loadUser(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User currentUsr = (User) ois.readObject();
        ois.close();
        return currentUsr;
    }
}
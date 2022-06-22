package com.example.computingclub;

import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import com.example.computingclub.userset.VisitHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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

    public static void changeOnClick(MouseEvent event, ListView<String> listView) throws IOException, ClassNotFoundException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        saveUser(currentUsr, "src/main/accounts/" + currentUsr.getName() + ".ser");

        String selected = listView.getSelectionModel().getSelectedItem();

        User current = loadUser("src/main/accounts/" + selected + ".ser");
        // load searched name's user into visitor
        VisitHolder visHolder = VisitHolder.getInstance();
        visHolder.setUser(current);
        // can't use the changescreen method because of mouseevent
        Parent nextScene = FXMLLoader.load(Objects.requireNonNull(Util.class.getResource("visitScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(nextScene);
        stage.setTitle("Perfil de " + current.getName());
        stage.setScene(scene);
        stage.show();
    }
}
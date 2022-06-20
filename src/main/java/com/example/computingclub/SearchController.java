package com.example.computingclub;

import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import com.example.computingclub.userset.VisitorHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Pane bgSearch;

    @FXML
    private TextField searchField;

    @FXML
    private Text searchText;

    @FXML
    void actionSearch(ActionEvent event) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream("src/main/accounts/" + searchField.getText() + ".ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User current = (User) objectInputStream.readObject();
        objectInputStream.close();
        if(current.getName().equals(searchField.getText())){

            VisitorHolder holder = VisitorHolder.getInstance();
            holder.setUser(current);

            Parent visit = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("visitScene.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(visit);
            stage.setTitle("Perfil de " + current.getName());
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        Parent profile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(profile);
        stage.setTitle("Perfil");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPosts();
    }

    void loadPosts() {
        bgSearch.setPrefHeight(searchText.getBoundsInLocal().getHeight() + 50);
    }

    @FXML
    void actionISearch(ActionEvent event) {

    }

}
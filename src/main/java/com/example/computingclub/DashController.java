package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashController implements Initializable {

    @FXML
    private Pane bgDash;

    @FXML
    private Text dashText;

    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        Parent profile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(profile);
        stage.setTitle("Perfil");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPosts();
    }


    void loadPosts() {
        bgDash.setPrefHeight(dashText.getBoundsInLocal().getHeight() + 50);
    }

}

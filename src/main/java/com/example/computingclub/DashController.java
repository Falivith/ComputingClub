package com.example.computingclub;

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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Pane bgDash;

    @FXML
    private Text dashText;

    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        Parent profile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        bgDash.setPrefHeight(dashText.getBoundsInLocal().getHeight() + 50);
    }

}

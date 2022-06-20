package com.example.computingclub;

import com.example.computingclub.userset.User;
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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactField1;

    @FXML
    private TextField contactField2;

    @FXML
    private TextField educationField;

    @FXML
    private Text followerText;

    @FXML
    private Text followingText;

    @FXML
    private TextField interestField1;

    @FXML
    private TextField interestField2;

    @FXML
    private TextField interestField3;

    @FXML
    private TextField interestField4;

    @FXML
    private TextField nameField;

    @FXML
    private TextField websiteField;

    @FXML
    private Pane bgFolloing;

    @FXML
    private Pane bgFollower;

    @FXML
    void actionLogout(ActionEvent event) {

    }

    @FXML
    void gotoDash(ActionEvent event) throws IOException {
        Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(dashboard);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        Parent search = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(search);
        stage.setTitle("Busca");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void actionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();
    }

    void loadFollows() {
        bgFolloing.setPrefHeight(followingText.getBoundsInLocal().getHeight() + 50);
        bgFollower.setPrefHeight(followerText.getBoundsInLocal().getHeight() + 50);
    }

}

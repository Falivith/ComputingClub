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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VisitController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Pane bgFolloing;

    @FXML
    private Pane bgFollower;

    @FXML
    private Text followerText;

    @FXML
    private Text followingText;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContact1;

    @FXML
    private Label lblContact2;

    @FXML
    private Label lblEducation;

    @FXML
    private Label lblInterest1;

    @FXML
    private Label lblInterest2;

    @FXML
    private Label lblInterest3;

    @FXML
    private Label lblInterest4;

    @FXML
    private Label lblName;

    @FXML
    private Label lblWebsite;

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        Parent search = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(search);
        stage.setTitle("Busca");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();

        VisitorHolder holder = VisitorHolder.getInstance();
        User current = holder.getUser();

        lblName.setText(current.getName());
        lblAddress.setText(current.getAddress());
        lblContact1.setText(current.getEmail());
        lblContact2.setText(current.getPhone());
        lblWebsite.setText(current.getPhone());
        lblEducation.setText(current.getEducation());
        lblInterest1.setText(current.getInterest1());
        lblInterest2.setText(current.getInterest2());
        lblInterest3.setText(current.getInterest3());
        lblInterest4.setText(current.getInterest4());
    }

    void loadFollows() {
        bgFolloing.setPrefHeight(followingText.getBoundsInLocal().getHeight() + 50);
        bgFollower.setPrefHeight(followerText.getBoundsInLocal().getHeight() + 50);
    }

}
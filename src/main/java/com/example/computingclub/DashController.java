package com.example.computingclub;

import com.example.computingclub.userset.Post;
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
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
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
        UserHolder uholder = UserHolder.getInstance();
        User actualu = uholder.getUser();
        VisitorHolder vholder = VisitorHolder.getInstance();
        User actualv = vholder.getUser();

        String dashholder = "";

        for (Post pos : actualv.getPosts()) {
            if (actualu.getFollowing().contains(pos.getAuthor())) {
                dashholder = dashholder + pos.getAuthor() + pos.getDate() + pos.getContent();
            }
        }
        dashText.setText(dashholder);

        bgDash.setPrefHeight(dashText.getBoundsInLocal().getHeight() + 50);
    }

}

package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import com.example.computingclub.userset.VisitHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.computingclub.Util.changeScreen;

public class DashController implements Initializable {
    @FXML
    private Pane bgDash;
    @FXML
    private Text dashText;

    //

    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        changeScreen(event, "profileScene.fxml", "Perfil");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();
        VisitHolder visHolder = VisitHolder.getInstance();
        User timeline = visHolder.getUser();

        String dashHolder = "= = = = = = = = = = = = = = = = = = = =\n\n";
        // formats and displays posts
        for (Post pos : timeline.getPosts()) {
            if (currentUsr.getFollowing().contains(pos.getAuthor())) {
                dashHolder = dashHolder + pos.getAuthor() + pos.getDate() + pos.getContent();
            }
        }
        dashText.setText(dashHolder);
        bgDash.setPrefHeight(dashText.getBoundsInLocal().getHeight());
    }
}

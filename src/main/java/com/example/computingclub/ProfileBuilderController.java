package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.computingclub.Util.changeScreen;
import static com.example.computingclub.Util.saveUser;

public class ProfileBuilderController {

    @FXML
    private TextField newUserField;
    @FXML
    private TextField newUserPassword;
    @FXML
    private Label userNotification;

    @FXML
    void btnCreate(ActionEvent event) throws IOException {

        String path = "src/main/accounts/" + newUserField.getText() + ".ser";
        File user_file = new File(path);

        if (!user_file.exists()) {
            // dummies setup
            Post dummyPost = new Post("", "", "");
            ArrayList<Post> dummyPostArray = new ArrayList<>();
            dummyPostArray.add(dummyPost);
            ArrayList<String> dummyFollower = new ArrayList<>();
            dummyFollower.add(newUserField.getText());
            ArrayList<String> dummyFollowing = new ArrayList<>();
            dummyFollowing.add(newUserField.getText());

            // creates a new user
            User newUser = new User(newUserField.getText(), newUserPassword.getText(), dummyPostArray, dummyFollower, dummyFollowing);
            saveUser(newUser, path);
            userNotification.setText("Usuário criado com sucesso");
        }else {
            userNotification.setText("Usuário já existe");
        }
        newUserField.setText("");
        newUserPassword.setText("");
    }

    @FXML
    void btnLogout(ActionEvent event) throws IOException {
        changeScreen(event, "loginScene.fxml", "Login");
    }
}
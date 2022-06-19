package com.example.computingclub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label welcomeText;

    @FXML
    void btnLoginAction(ActionEvent event) {
        if(loginField.getText().equals("admin") && passwordField.getText().equals("admin"));

    }
}
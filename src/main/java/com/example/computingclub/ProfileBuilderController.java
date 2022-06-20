package com.example.computingclub;

import com.example.computingclub.userset.PersistentData;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class ProfileBuilderController {

    @FXML
    private TextField newUserField;

    @FXML
    private TextField newUserPassword;

    @FXML
    private Label userNotification;

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        User newUser = new User(PersistentData.getUserCount(), newUserField.getText(), newUserPassword.getText());
        String file_path = "src/main/java/com/example/computingclub/userset/" + newUser.getId() + ".ser";
        FileOutputStream fOut = new FileOutputStream(file_path);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(newUser);
        oOut.close();
        userNotification.setText("Usuário com ID " + newUser.getId() + " criado com Sucesso");
    }

    @FXML
    void btnLogout(ActionEvent event) throws IOException {
        Parent adminLogout = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(adminLogout);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


}
package com.example.computingclub;

import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField loginField;


    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (loginField.getText().equals("admin") && passwordField.getText().equals("UFP31")) {

            //Switch to Profile Builder window

            Parent adminEnter = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileBuilderScene.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(adminEnter);
            stage.setTitle("Criação de Perfis (Admin)");
            stage.setScene(scene);
            stage.show();
        }
        else{
            FileInputStream fileInputStream = new FileInputStream("src/main/accounts/" + loginField.getText() + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            User actual = (User) objectInputStream.readObject();
            objectInputStream.close();
            if(actual.getPassword().equals(passwordField.getText())){

                UserHolder holder = UserHolder.getInstance();
                holder.setUser(actual);

                Parent enterSuccess = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileScene.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(enterSuccess);
                stage.setTitle("Editar Perfil");
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
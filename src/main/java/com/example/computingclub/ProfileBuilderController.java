package com.example.computingclub;

import com.example.computingclub.userset.AdminPersistentData;
import com.example.computingclub.userset.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileBuilderController implements Initializable {

    @FXML
    private TextField newUserField;

    @FXML
    private TextField newUserPassword;

    @FXML
    private Label userNotification;

    @FXML
    void btnCreate(ActionEvent event) throws IOException, ClassNotFoundException {

        //Check if the file exists, if not, he made it
        File file = new File("src/main/accounts/idCount.ser");
        if (!file.exists()){
            FileOutputStream idStream = new FileOutputStream("src/main/accounts/idCount.ser");
            ObjectOutputStream out = new ObjectOutputStream(idStream);
            out.writeObject(AdminPersistentData.getUserCount(false));
            out.close();
        }

        FileInputStream fis = new FileInputStream("src/main/accounts/idCount.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        AdminPersistentData.setUserCount((Integer)ois.readObject());
        ois.close();

        User newUser = new User(AdminPersistentData.getUserCount(true), newUserField.getText(), newUserPassword.getText());
        String file_path = "src/main/accounts/" + newUser.getName() + ".ser";
        FileOutputStream fOut = new FileOutputStream(file_path);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(newUser);
        oOut.close();
        userNotification.setText("Usuário com ID " + newUser.getId() + " criado com Sucesso");

        //Save Actual ID
        FileOutputStream idStream = new FileOutputStream("src/main/accounts/idCount.ser");
        ObjectOutputStream out = new ObjectOutputStream(idStream);
        out.writeObject(AdminPersistentData.getUserCount(false));
        out.close();
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

    public void initialize(URL location, ResourceBundle resources) {
    }


}
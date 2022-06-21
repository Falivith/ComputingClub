package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    //

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (loginField.getText().equals("admin") && passwordField.getText().equals("UFP31")) {
            String path = "src/main/admin/admin.ser";
            File user_file = new File(path);
            if (!user_file.exists()) {
                Post dummyPost = new Post("", "", "");
                ArrayList<Post> dummyPostArray = new ArrayList<>();
                dummyPostArray.add(dummyPost);
                ArrayList<String> dummyFollow = new ArrayList<>();
                dummyFollow.add("");
                // creates a new user
                User admin = new User("", "", dummyPostArray, dummyFollow, dummyFollow);

                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(admin);
                oos.close();
            }
            Parent criaperfil = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileBuilderScene.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(criaperfil);
            stage.setTitle("ADMIN - Criação de perfil");
            stage.setScene(scene);
            stage.show();
        }
        else{
            FileInputStream fis = new FileInputStream("src/main/accounts/" + loginField.getText() + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            User currentUsr = (User) ois.readObject();
            ois.close();

            UserHolder usrHolder = UserHolder.getInstance();
            usrHolder.setUser(currentUsr);

            if (currentUsr.getPassword().equals(passwordField.getText())) {
                Parent perfil = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileScene.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(perfil);
                stage.setTitle("Perfil");
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
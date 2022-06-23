package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.*;
import java.util.ArrayList;

import static com.example.computingclub.Util.*;

public class LoginController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label lblUserNotification;

    //

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String path;

        if (loginField.getText().equals("admin") && passwordField.getText().equals("UFP31")) {
            path = "src/main/admin/admin.ser";
            File user_file = new File("src/main/admin/admin.ser");
            // creates admin file if it doesn't exist
            if (!user_file.exists()) {
            // setup dummies
                Post dummyPost = new Post("", "", "");
                ArrayList<Post> dummyPostArray = new ArrayList<>();
                dummyPostArray.add(dummyPost);
                ArrayList<String> dummyFollow = new ArrayList<>();
                dummyFollow.add("");

                User admin = new User("", "", dummyPostArray, dummyFollow, dummyFollow);

                File default_pfp = new File("src/main/default.png");
                admin.setImgPath(default_pfp.getAbsolutePath());

                saveUser(admin, path);
                // deletes deletemes if they exist
                File deleteme1 = new File("src/main/admin/deleteme");
                File deleteme2 = new File("src/main/accounts/deleteme");
                if (deleteme1.exists()) {
                    deleteme1.delete();
                }
                if (deleteme2.exists()) {
                    deleteme2.delete();
                }
            }
            changeScreen(event, "profileBuilderScene.fxml", "ADMIN - Criação");
        }else if (!loginField.getText().equals("admin")){
            path = "src/main/accounts/" + loginField.getText() + ".ser";
            File user_file = new File(path);
            if (user_file.exists()) {
                User currentUsr = loadUser(path);
                UserHolder usrHolder = UserHolder.getInstance();
                usrHolder.setUser(currentUsr);

                if (currentUsr.getPassword().equals(passwordField.getText())) {
                    changeScreen(event, "profileScene.fxml", "Perfil");
                }else {
                    lblUserNotification.setText("Senha incorreta");
                }
            }else {
                lblUserNotification.setText("Usuário não existe");
            }
        }else {
            lblUserNotification.setText("Senha incorreta");
        }
    }
}
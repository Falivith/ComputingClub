package com.example.computingclub;

import com.example.computingclub.userset.User;
import com.example.computingclub.userset.VisitHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.computingclub.Util.changeScreen;
import static com.example.computingclub.Util.loadUser;

public class SearchController implements Initializable {
    @FXML
    private ListView<String> listViewUsers;
    private final List<String> users = new ArrayList<>();
    @FXML
    private TextField searchField;
    @FXML
    private Label lblUserNotification;

    //

    @FXML
    void actionSearch(ActionEvent event) throws IOException, ClassNotFoundException{
        String path = "src/main/accounts/" + searchField.getText() + ".ser";
        File user_file = new File(path);

        if (user_file.exists()) {
            User current = loadUser("src/main/accounts/" + searchField.getText() + ".ser");
            // load searched name's user into visitor
            VisitHolder visHolder = VisitHolder.getInstance();
            visHolder.setUser(current);

            changeScreen(event, "visitScene.fxml", "Perfil de " + current.getName());
        }else {
            lblUserNotification.setText("Usuário não existe");
        }
    }

    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        changeScreen(event, "profileScene.fxml", "Perfil");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUsers();
    }

    void loadUsers(){
        File file = new File("src/main/accounts/");
        File[] arquivos = file.listFiles();
        try {
            for (File arquivo : arquivos) {
                users.add((arquivo.getName().replace(".ser", "")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<String> obsUsers = FXCollections.observableArrayList(users);
        listViewUsers.setItems(obsUsers);
    }
}
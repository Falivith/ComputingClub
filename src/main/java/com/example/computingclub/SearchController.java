package com.example.computingclub;

import com.example.computingclub.userset.User;
import com.example.computingclub.userset.VisitHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.computingclub.Util.*;

public class SearchController implements Initializable {
    @FXML
    private ListView<String> listViewUsers;
    private final List<String> users = new ArrayList<>();
    @FXML
    private TextField filterField;

    //

    @FXML
    void actionFilter(ActionEvent event) {
        final List<String> users = new ArrayList<>();

        File file = new File("src/main/accounts/");
        File[] usrFile = file.listFiles();
        try {
            for (File fl : usrFile) {

                User test = loadUser("src/main/accounts/" + fl.getName());

                if (filterField.getText().equals(test.getInterest1()) ||
                    filterField.getText().equals(test.getInterest2()) ||
                    filterField.getText().equals(test.getInterest3()) ||
                    filterField.getText().equals(test.getInterest4()) ||
                    filterField.getText().equals(test.getName())) {
                    users.add((fl.getName().replace(".ser", "")));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<String> obsUsers = FXCollections.observableArrayList(users);
        listViewUsers.setItems(obsUsers);
        users.clear();
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


    @FXML
    void actionSelect(MouseEvent event) throws IOException, ClassNotFoundException {
        changeOnClick(event, listViewUsers);
    }
}
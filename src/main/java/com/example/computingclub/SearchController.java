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
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private ListView<String> listViewUsers;
    private final List<String> users = new ArrayList<>();
    @FXML
    private TextField searchField;

    @FXML
    void actionSearch(ActionEvent event) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("src/main/accounts/" + searchField.getText() + ".ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User current = (User) ois.readObject();
        ois.close();
        // load searched name's user into visitor
        VisitHolder visHolder = VisitHolder.getInstance();
        visHolder.setUser(current);

        Parent visit = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("visitScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(visit);
        stage.setTitle("Perfil de " + current.getName());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        Parent perfil = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(perfil);
        stage.setTitle("Perfil");
        stage.setScene(scene);
        stage.show();
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
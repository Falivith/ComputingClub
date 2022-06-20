package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.Timeline;
import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProfileController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactField1;

    @FXML
    private TextField contactField2;

    @FXML
    private TextField educationField;

    @FXML
    private Text followerText;

    @FXML
    private Text followingText;

    @FXML
    private TextField interestField1;

    @FXML
    private TextField interestField2;

    @FXML
    private TextField interestField3;

    @FXML
    private TextField interestField4;

    @FXML
    private TextField nameField;

    @FXML
    private TextField websiteField;

    @FXML
    private Pane bgFolloing;

    @FXML
    private Pane bgFollower;

    @FXML
    private TextArea postField;

    ArrayList<String> timeline = new ArrayList<>();

    @FXML
    void actionLogout(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(login);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoDash(ActionEvent event) throws IOException {
        Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(dashboard);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        Parent search = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(search);
        stage.setTitle("Busca");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void actionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();
        timeline.add("fim");
    }

    void loadFollows() {
        bgFolloing.setPrefHeight(followingText.getBoundsInLocal().getHeight() + 50);
        bgFollower.setPrefHeight(followerText.getBoundsInLocal().getHeight() + 50);
    }

    @FXML
    void actionPost(ActionEvent event) {

        UserHolder holder = UserHolder.getInstance();
        User current = holder.getUser();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        timeline.add(0, current.getName() + " - " + formatter.format(date) + "\n" + postField.getText() + "\n\n");

        for (int i = 0; i >= 0; i++) {
            if (timeline.get(i) != "fim") {
                System.out.println(timeline.get(i));
            }else {
                break;
            }
        }
    }

}

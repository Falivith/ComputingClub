package com.example.computingclub;

import com.example.computingclub.userset.AdminPersistentData;
import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
    private Label userNotification;

    @FXML
    void actionLogout(ActionEvent event) throws IOException {

        UserHolder holder = UserHolder.getInstance();
        User actual = holder.getUser();

        Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(dashboard);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        String file_path = "src/main/accounts/" + actual.getName() + ".ser";
        FileOutputStream fOut = new FileOutputStream(file_path);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(actual);
        oOut.close();
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
        UserHolder holder = UserHolder.getInstance();
        User actual = holder.getUser();

        actual.setName(nameField.getText());
        actual.setAddress(addressField.getText());
        actual.setEmail(contactField1.getText());
        actual.setPhone(contactField2.getText());
        actual.setWebsite(websiteField.getText());
        actual.setEducation(educationField.getText());
        actual.setInterest1(interestField1.getText());
        actual.setInterest2(interestField2.getText());
        actual.setInterest3(interestField3.getText());
        actual.setInterest4(interestField4.getText());

        holder.setUser(actual);
        userNotification.setText("Informações\nSalvas com\nSucesso");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();

        UserHolder holder = UserHolder.getInstance();
        User actual = holder.getUser();

        nameField.setText(actual.getName());
        addressField.setText(actual.getAddress());
        contactField1.setText(actual.getEmail());
        contactField2.setText(actual.getPhone());
        websiteField.setText(actual.getPhone());
        educationField.setText(actual.getEducation());
        interestField1.setText(actual.getInterest1());
        interestField2.setText(actual.getInterest2());
        interestField3.setText(actual.getInterest3());
        interestField4.setText(actual.getInterest4());
    }

    void loadFollows() {
        bgFolloing.setPrefHeight(followingText.getBoundsInLocal().getHeight() + 50);
        bgFollower.setPrefHeight(followerText.getBoundsInLocal().getHeight() + 50);
    }

}

package com.example.computingclub;

import com.example.computingclub.userset.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
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
    private Label userNotification;

    @FXML
    private TextArea postField;


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
    void gotoDash(ActionEvent event) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream("src/main/idcount/dummy.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User current = (User) objectInputStream.readObject();
        objectInputStream.close();

        VisitorHolder holder = VisitorHolder.getInstance();
        holder.setUser(current);

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
    void actionSave(ActionEvent event) throws IOException {
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

        String file_path = "src/main/accounts/" + actual.getName() + ".ser";
        FileOutputStream fOut = new FileOutputStream(file_path);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(actual);
        oOut.close();

        userNotification.setVisible(true);
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
        websiteField.setText(actual.getWebsite());
        educationField.setText(actual.getEducation());
        interestField1.setText(actual.getInterest1());
        interestField2.setText(actual.getInterest2());
        interestField3.setText(actual.getInterest3());
        interestField4.setText(actual.getInterest4());
    }

    void loadFollows() {
        UserHolder uholder = UserHolder.getInstance();
        User actual = uholder.getUser();

        String followholder = "";

        for (String pos : actual.getFollowers()) {
            followholder = followholder + pos + "\n";
        }
        followerText.setText(followholder);

        followholder = "";

        for (String pos : actual.getFollowing()) {
            followholder = followholder + pos + "\n";
        }
        followingText.setText(followholder);

        bgFolloing.setPrefHeight(followingText.getBoundsInLocal().getHeight() + 50);
        bgFollower.setPrefHeight(followerText.getBoundsInLocal().getHeight() + 50);
    }

    @FXML
    void actionPost(ActionEvent event) throws IOException, ClassNotFoundException {

        UserHolder holder = UserHolder.getInstance();
        User actual = holder.getUser();

        FileInputStream fileInputStream = new FileInputStream("src/main/idcount/dummy.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User timeline = (User) objectInputStream.readObject();
        objectInputStream.close();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Post post = new Post(actual.getName(), "\n" + postField.getText() + "\n\n", " - " + formatter.format(date));
        actual.getPosts().add(0, post);
        timeline.getPosts().add(0, post);

        holder.setUser(actual);

        String file_path = "src/main/idcount/dummy.ser";
        FileOutputStream fOut = new FileOutputStream(file_path);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(timeline);
        oOut.close();
    }

}

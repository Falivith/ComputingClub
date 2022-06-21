package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import com.example.computingclub.userset.VisitorHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class VisitController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Pane bgFolloing;

    @FXML
    private Pane bgFollower;

    @FXML
    private Text followerText;

    @FXML
    private Text followingText;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContact1;

    @FXML
    private Label lblContact2;

    @FXML
    private Label lblEducation;

    @FXML
    private Label lblInterest1;

    @FXML
    private Label lblInterest2;

    @FXML
    private Label lblInterest3;

    @FXML
    private Label lblInterest4;

    @FXML
    private Label lblName;

    @FXML
    private Label lblWebsite;

    @FXML
    private Button btnFollow;

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        VisitorHolder holder = VisitorHolder.getInstance();
        User actualv = holder.getUser();
        UserHolder uholder = UserHolder.getInstance();
        User actualu = uholder.getUser();

        Parent search = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(search);
        stage.setTitle("Busca");
        stage.setScene(scene);
        stage.show();

        String file_path = "src/main/accounts/" + actualv.getName() + ".ser";
        FileOutputStream fOut = new FileOutputStream(file_path);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(actualv);
        oOut.close();

        file_path = "src/main/accounts/" + actualu.getName() + ".ser";
        fOut = new FileOutputStream(file_path);
        oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(actualu);
        oOut.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();

        VisitorHolder holder = VisitorHolder.getInstance();
        User actual = holder.getUser();

        lblName.setText(actual.getName());
        lblAddress.setText(actual.getAddress());
        lblContact1.setText(actual.getEmail());
        lblContact2.setText(actual.getPhone());
        lblWebsite.setText(actual.getWebsite());
        lblEducation.setText(actual.getEducation());
        lblInterest1.setText(actual.getInterest1());
        lblInterest2.setText(actual.getInterest2());
        lblInterest3.setText(actual.getInterest3());
        lblInterest4.setText(actual.getInterest4());
    }

    void loadFollows() {
        VisitorHolder vholder = VisitorHolder.getInstance();
        User actualv = vholder.getUser();
        UserHolder uholder = UserHolder.getInstance();
        User actualu = uholder.getUser();

        String followholder = "";

        for (String pos : actualv.getFollowers()) {
            followholder = followholder + pos + "\n";
        }
        followerText.setText(followholder);

        followholder = "";

        for (String pos : actualv.getFollowing()) {
            followholder = followholder + pos + "\n";
        }
        followingText.setText(followholder);

        bgFolloing.setPrefHeight(followingText.getBoundsInLocal().getHeight() + 10);
        bgFollower.setPrefHeight(followerText.getBoundsInLocal().getHeight() + 10);

        if (actualu.getName().equals(actualv.getName())) {
            btnFollow.setVisible(false);
        } else if (actualv.getFollowers().contains(actualu.getName())) {
            btnFollow.setText("Deixar de seguir");
        } else {
            btnFollow.setText("Seguir");
        }
    }

    @FXML
    void actionFollow(ActionEvent event) {
        VisitorHolder vholder = VisitorHolder.getInstance();
        User actualv = vholder.getUser();
        UserHolder uholder = UserHolder.getInstance();
        User actualu = uholder.getUser();

        if (!actualv.getFollowers().contains(actualu.getName())) {
            actualv.getFollowers().add(actualu.getName());
            actualu.getFollowing().add(actualv.getName());
            uholder.setUser(actualu);
            vholder.setUser(actualv);

            loadFollows();
        } else {
            actualv.getFollowers().remove(actualu.getName());
            actualu.getFollowing().remove(actualv.getName());
            uholder.setUser(actualu);
            vholder.setUser(actualv);

            loadFollows();
        }
    }

}
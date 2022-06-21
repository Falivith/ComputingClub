package com.example.computingclub;

import com.example.computingclub.userset.Post;
import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
import com.example.computingclub.userset.VisitHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.computingclub.Util.changeScreen;
import static com.example.computingclub.Util.saveUser;

public class VisitController implements Initializable {
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
    private ListView<String> listFollowers;
    @FXML
    private ListView<String> listFollowing;
    @FXML
    private Pane bgDash;
    @FXML
    private Text dashText;

    //

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();
        VisitHolder visHolder = VisitHolder.getInstance();
        User currentVis = visHolder.getUser();

        saveUser(currentVis, "src/main/accounts/" + currentVis.getName() + ".ser");
        saveUser(currentUsr, "src/main/accounts/" + currentUsr.getName() + ".ser");

        changeScreen(event, "searchScene.fxml", "Busca");
    }

    @FXML
    void actionFollow(ActionEvent event) {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();
        VisitHolder visHolder = VisitHolder.getInstance();
        User currentVis = visHolder.getUser();
        // updates both profiles follow/er list
        if (!currentVis.getFollowers().contains(currentUsr.getName())) {
            currentVis.getFollowers().add(currentUsr.getName());
            currentUsr.getFollowing().add(currentVis.getName());

        } else {
            currentVis.getFollowers().remove(currentUsr.getName());
            currentUsr.getFollowing().remove(currentVis.getName());

        }
        usrHolder.setUser(currentUsr);
        visHolder.setUser(currentVis);
        loadFollows();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();

        VisitHolder visHolder = VisitHolder.getInstance();
        User currentVis = visHolder.getUser();
        // loads and displays profile's info
        lblName.setText(currentVis.getName());
        lblAddress.setText(currentVis.getAddress());
        lblContact1.setText(currentVis.getContact1());
        lblContact2.setText(currentVis.getContact2());
        lblWebsite.setText(currentVis.getWebsite());
        lblEducation.setText(currentVis.getEducation());
        lblInterest1.setText(currentVis.getInterest1());
        lblInterest2.setText(currentVis.getInterest2());
        lblInterest3.setText(currentVis.getInterest3());
        lblInterest4.setText(currentVis.getInterest4());
        // display all the user's posts
        String dashHolder = "= = = = = = = = = = = = = = = = = = = =\n\n";

        for (Post pos : currentVis.getPosts()) {
            dashHolder = dashHolder + pos.getAuthor() + pos.getDate() + pos.getContent();
        }
        dashText.setText(dashHolder);
        bgDash.setPrefHeight(dashText.getBoundsInLocal().getHeight());
    }

    void loadFollows() {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();
        VisitHolder visHolder = VisitHolder.getInstance();
        User currentVis = visHolder.getUser();

        final List<String> users = new ArrayList<>();

        File file = new File("src/main/accounts/");
        File[] usrFile = file.listFiles();
        try {
            for (File fl : usrFile) {
                if (currentVis.getFollowing().contains(fl.getName().replace(".ser", ""))) {
                    users.add((fl.getName().replace(".ser", "")));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<String> obsUsers = FXCollections.observableArrayList(users);
        listFollowing.setItems(obsUsers);

        users.clear();

        try {
            for (File fl : usrFile) {
                if (currentVis.getFollowers().contains(fl.getName().replace(".ser", ""))) {
                    users.add((fl.getName().replace(".ser", "")));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        obsUsers = FXCollections.observableArrayList(users);
        listFollowers.setItems(obsUsers);

        users.clear();

        if (currentUsr.getName().equals(currentVis.getName())) {
            btnFollow.setVisible(false);
        } else if (currentVis.getFollowers().contains(currentUsr.getName())) {
            btnFollow.setText("Deixar de seguir");
        } else {
            btnFollow.setText("Seguir");
        }
    }
}
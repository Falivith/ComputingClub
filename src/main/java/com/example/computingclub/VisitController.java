package com.example.computingclub;

import com.example.computingclub.userset.User;
import com.example.computingclub.userset.UserHolder;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class VisitController implements Initializable {

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
    private ListView<String> listFollowers;

    @FXML
    private ListView<String> listFollowing;

    //

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();
        VisitHolder visHolder = VisitHolder.getInstance();
        User currentVis = visHolder.getUser();

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Busca");
        stage.setScene(scene);
        stage.show();

        String path = "src/main/accounts/" + currentVis.getName() + ".ser";
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentVis);
        oos.close();

        path = "src/main/accounts/" + currentUsr.getName() + ".ser";
        fos = new FileOutputStream(path);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(currentUsr);
        oos.close();
    }

    @FXML
    void actionFollow(ActionEvent event) {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();
        VisitHolder visHolder = VisitHolder.getInstance();
        User currentVis = visHolder.getUser();

        if (!currentVis.getFollowers().contains(currentUsr.getName())) {
            currentVis.getFollowers().add(currentUsr.getName());
            currentUsr.getFollowing().add(currentVis.getName());
            usrHolder.setUser(currentUsr);
            visHolder.setUser(currentVis);

            loadFollows();
        } else {
            currentVis.getFollowers().remove(currentUsr.getName());
            currentUsr.getFollowing().remove(currentVis.getName());
            usrHolder.setUser(currentUsr);
            visHolder.setUser(currentVis);

            loadFollows();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();

        VisitHolder visHolder = VisitHolder.getInstance();
        User currentVis = visHolder.getUser();

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
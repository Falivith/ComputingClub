package com.example.computingclub;

import com.example.computingclub.userset.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.computingclub.Util.*;

public class ProfileController implements Initializable {
    @FXML
    private TextField addressField;
    @FXML
    private TextField contactField1;
    @FXML
    private TextField contactField2;
    @FXML
    private TextField educationField;
    @FXML
    private TextField interestField1;
    @FXML
    private TextField interestField2;
    @FXML
    private TextField interestField3;
    @FXML
    private TextField interestField4;
    @FXML
    private TextField websiteField;
    @FXML
    private Label userNotification;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPost;
    @FXML
    private TextArea postField;
    @FXML
    private ListView<String> listFollowers;
    @FXML
    private ListView<String> listFollowing;

    @FXML
    private ImageView imgContainer;

    //

    @FXML
    void actionLogout(ActionEvent event) throws IOException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        saveUser(currentUsr, "src/main/accounts/" + currentUsr.getName() + ".ser");
        changeScreen(event, "loginScene.fxml", "Login");
    }

    @FXML
    void gotoDash(ActionEvent event) throws IOException, ClassNotFoundException{
        User timeline = loadUser("src/main/admin/admin.ser");
        // repurpose of visit into a timeline using admin
        VisitHolder visHolder = VisitHolder.getInstance();
        visHolder.setUser(timeline);

        changeScreen(event, "dashScene.fxml", "Dashboard");
    }

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        saveUser(currentUsr, "src/main/accounts/" + currentUsr.getName() + ".ser");

        changeScreen(event, "searchScene.fxml", "Busca");
    }

    @FXML
    void actionSave(ActionEvent event) throws IOException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();
        // saves all changes into user
        currentUsr.setName(lblName.getText());
        currentUsr.setAddress(addressField.getText());
        currentUsr.setContact1(contactField1.getText());
        currentUsr.setContact2(contactField2.getText());
        currentUsr.setWebsite(websiteField.getText());
        currentUsr.setEducation(educationField.getText());
        currentUsr.setInterest1(interestField1.getText());
        currentUsr.setInterest2(interestField2.getText());
        currentUsr.setInterest3(interestField3.getText());
        currentUsr.setInterest4(interestField4.getText());

        saveUser(currentUsr, "src/main/accounts/" + currentUsr.getName() + ".ser");

        userNotification.setVisible(true);
    }

    @FXML
    void changePhoto(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Imagem");


        currentUsr.setImgPath(String.valueOf(fileChooser.showOpenDialog(stage)));

        imgContainer.setImage(new Image(String.valueOf(currentUsr.getImgPath())));
        System.out.println(currentUsr.getImgPath());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();

        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        // loads and displays user info
        lblName.setText(currentUsr.getName());
        addressField.setText(currentUsr.getAddress());
        contactField1.setText(currentUsr.getContact1());
        contactField2.setText(currentUsr.getContact2());
        websiteField.setText(currentUsr.getWebsite());
        educationField.setText(currentUsr.getEducation());
        interestField1.setText(currentUsr.getInterest1());
        interestField2.setText(currentUsr.getInterest2());
        interestField3.setText(currentUsr.getInterest3());
        interestField4.setText(currentUsr.getInterest4());

        imgContainer.setImage(new Image(String.valueOf(currentUsr.getImgPath())));
    }

    @FXML
    void actionPost(ActionEvent event) throws IOException, ClassNotFoundException {
        if (postField.getText() != null && !postField.getText().trim().isEmpty()) {
            UserHolder usrHolder = UserHolder.getInstance();
            User currentUsr = usrHolder.getUser();

            User timeline = loadUser("src/main/admin/admin.ser");

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            Post post = new Post(currentUsr.getName(), "\n" + postField.getText() + "\n\n= = = = = = = = = = = = = = = = = = = =\n\n", " - " + formatter.format(date));
            currentUsr.getPosts().add(0, post);
            timeline.getPosts().add(0, post);

            usrHolder.setUser(currentUsr);
            saveUser(timeline, "src/main/admin/admin.ser");

            lblPost.setVisible(true);
            postField.setText("");
        }else {
            lblPost.setVisible(false);
        }
    }

    void loadFollows() {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        final List<String> users = new ArrayList<>();

        File file = new File("src/main/accounts/");
        File[] usrFile = file.listFiles();
        try {
            for (File fl : usrFile) {
                if (currentUsr.getFollowing().contains(fl.getName().replace(".ser", ""))) {
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
                if (currentUsr.getFollowers().contains(fl.getName().replace(".ser", ""))) {
                    users.add((fl.getName().replace(".ser", "")));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        obsUsers = FXCollections.observableArrayList(users);
        listFollowers.setItems(obsUsers);

        users.clear();
    }

    @FXML
    void actionSelectFollower(MouseEvent event) throws IOException, ClassNotFoundException {
        changeOnClick(event, listFollowers);
    }

    @FXML
    void actionSelectFollowing(MouseEvent event) throws IOException, ClassNotFoundException {
        changeOnClick(event, listFollowing);
    }
}

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
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

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

    //

    @FXML
    void actionLogout(ActionEvent event) throws IOException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        FileOutputStream fos = new FileOutputStream("src/main/accounts/" + currentUsr.getName() + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentUsr);
        oos.close();

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoDash(ActionEvent event) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("src/main/admin/admin.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User timeline = (User) ois.readObject();
        ois.close();
        // repurpose of visit into a timeline using admin
        VisitHolder visHolder = VisitHolder.getInstance();
        visHolder.setUser(timeline);

        Parent dash = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(dash);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoSearch(ActionEvent event) throws IOException {
        Parent busca = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(busca);
        stage.setTitle("Busca");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void actionSave(ActionEvent event) throws IOException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

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

        String file_path = "src/main/accounts/" + currentUsr.getName() + ".ser";
        FileOutputStream fOut = new FileOutputStream(file_path);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(currentUsr);
        oOut.close();

        userNotification.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFollows();

        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

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
    void actionPost(ActionEvent event) throws IOException, ClassNotFoundException {
        UserHolder usrHolder = UserHolder.getInstance();
        User currentUsr = usrHolder.getUser();

        String path = "src/main/admin/admin.ser";
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User timeline = (User) ois.readObject();
        ois.close();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Post post = new Post(currentUsr.getName(), "\n" + postField.getText() + "\n\n= = = = = = = = = = = = = = = = = = = =\n\n", " - " + formatter.format(date));
        currentUsr.getPosts().add(0, post);
        timeline.getPosts().add(0, post);

        usrHolder.setUser(currentUsr);

        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(timeline);
        oos.close();

        lblPost.setVisible(true);
        postField.setText("");
    }
}

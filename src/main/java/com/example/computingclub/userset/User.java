package com.example.computingclub.userset;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String password;
    private ArrayList<Post> posts;
    private ArrayList<String> followers;
    private ArrayList<String> following;

    //

    private String imgPath = "C:\\default.png";
    private String address = "";
    private String contact1 = "";
    private String contact2 = "";
    private String website = "";
    private String education = "";
    private String interest1 = "";
    private String interest2 = "";
    private String interest3 = "";
    private String interest4 = "";

    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }

    public String getInterest4() {
        return interest4;
    }

    public void setInterest4(String interest4) {
        this.interest4 = interest4;
    }

    public String getPassword() {
        return password;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    //

    public User(String name,
                String password,
                ArrayList<Post> posts,
                ArrayList<String> followers,
                ArrayList<String> following
                ){
        this.name = name;
        this.password = password;

        this.posts = posts;
        this.followers = followers;
        this.following = following;
    }

}

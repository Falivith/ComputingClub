package com.example.computingclub.userset;

import java.io.Serializable;

public class Post implements Serializable {
    private String author;
    private String content;
    private String date;

    //

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    //

    public Post(String author, String content, String date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }
}

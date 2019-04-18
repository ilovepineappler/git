package com.example.pc.myapplication;

public class Post {

    private String title;
    private String content;

    private String id;

    public Post() {

    }

    public Post(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;

    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}

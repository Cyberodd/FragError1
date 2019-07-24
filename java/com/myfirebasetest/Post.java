package com.myfirebasetest;

public class Post {
    private String name, title, content;

    public Post() {

    }

    public Post(String name, String title, String content) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package com.example.spotapp.dto;

import com.google.gson.annotations.SerializedName;

public class Inquiry {

    @SerializedName("title")
    private String title;

    @SerializedName("name")
    private String name;

    @SerializedName("content")
    private String content;

    public Inquiry(String title, String name, String content) {
        this.title = title;
        this.name = name;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Inquiry { title=" + title + ", name=" + name + ", content=" + content + " }";
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.example.spotapp.dto;

import com.google.gson.annotations.SerializedName;

public class InquiryCommentData {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("content")
    private String content;

    @SerializedName("regDate")
    private String regDate;

    @Override
    public String toString() {
        return "InquiryCommentData{ id:" + id + " name:" + name + " content:" + content + " regDate:" + regDate + "}";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getRegDate() {
        return regDate;
    }
}

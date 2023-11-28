package com.example.spotapp.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class InquiryData {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("regDate")
    private String regDate;

    @Override
    public String toString() {
        return "InquiryData{ id:" + id + " name:" + name + " title:" + title + " content:" + content + " regDate:" + regDate + "}";
    }
}

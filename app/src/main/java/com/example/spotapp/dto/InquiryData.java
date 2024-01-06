package com.example.spotapp.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

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

    @SerializedName("images")
    private List<ImageFile> images;

    @Override
    public String toString() {
        String str = "InquiryData{ id:" + id + " name:" + name + " title:" + title + " content:" + content + " regDate:" + regDate + " fileNameList:";

        if(images!=null) {
            for (ImageFile image : images) {
                str+=image;
            }
        }

        str+="}";
        return str;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageFile> getImages() {
        return images;
    }
}

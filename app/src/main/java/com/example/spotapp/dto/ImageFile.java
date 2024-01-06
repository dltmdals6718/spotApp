package com.example.spotapp.dto;

import com.google.gson.annotations.SerializedName;

public class ImageFile {

    @SerializedName("id")
    private String id;

    @SerializedName("inquiryId")
    private String inquiryId;

    @SerializedName("uploadFileName")
    private String uploadFileName;

    @SerializedName("storeFileName")
    private String storeFileName;


    @Override
    public String toString() {
        return "ImageFile{" +
                "id='" + id + '\'' +
                ", inquiryId='" + inquiryId + '\'' +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", storeFileName='" + storeFileName + '\'' +
                '}';
    }

    public String getStoreFileName() {
        return storeFileName;
    }
}

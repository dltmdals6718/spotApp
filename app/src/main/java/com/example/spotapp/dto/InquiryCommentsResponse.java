package com.example.spotapp.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InquiryCommentsResponse {

    private String status;
    private List<InquiryCommentData> data;
    private String message;

    public String getStatus() {
        return status;
    }

    public List<InquiryCommentData> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}

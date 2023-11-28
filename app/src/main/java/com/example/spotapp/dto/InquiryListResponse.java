package com.example.spotapp.dto;

import java.util.List;

public class InquiryListResponse {

    private String status;
    private List<InquiryData> data;
    private String message;

    public String getStatus() {
        return status;
    }

    public List<InquiryData> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}

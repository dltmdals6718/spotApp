package com.example.spotapp.retrofit.Interface;

import com.example.spotapp.dto.Inquiry;
import com.example.spotapp.dto.InquiryCommentsResponse;
import com.example.spotapp.dto.InquiryData;
import com.example.spotapp.dto.InquiryListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InquiryRetrofit {

    @POST("/inquirys")
    Call<Inquiry> addInquiry(@Body Inquiry inquiry);

    @GET("/inquirys/{id}")
    Call<InquiryListResponse> getInquiry(@Path("id") Long id);

    @GET("/inquirys")
    Call<InquiryListResponse> getInquirys();

    @GET("/comments/{inquiry_id}")
    Call<InquiryCommentsResponse> getComments(@Path("inquiry_id") Long inquiry_id);
}

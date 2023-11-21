package com.example.spotapp.retrofit.Interface;

import com.example.spotapp.dto.Inquiry;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InquiryRetrofit {

    @POST("/inquirys")
    Call<Inquiry> addInquiry(@Body Inquiry inquiry);

}

package com.example.spotapp.retrofit.Interface;

import com.example.spotapp.dto.CommonResponse;
import com.example.spotapp.dto.Inquiry;
import com.example.spotapp.dto.InquiryCommentData;
import com.example.spotapp.dto.InquiryData;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface InquiryRetrofit {

    @POST("/inquirys")
    @Multipart
    Call<Inquiry> addInquiry(@Part("inquiry") Inquiry inquiry, @Part List<MultipartBody.Part> files);

    @GET("/inquirys/{id}")
    Call<CommonResponse<List<InquiryData>>> getInquiry(@Path("id") Long id);

    @GET("/inquirys")
    Call<CommonResponse<List<InquiryData>>> getInquirys();

    @GET("/comments/{inquiry_id}")
    Call<CommonResponse<List<InquiryCommentData>>> getComments(@Path("inquiry_id") Long inquiry_id);

    @POST("/comments/{inquiry_id}")
    Call<CommonResponse<InquiryCommentData>> addComment(@Path("inquiry_id") Long inquiry_id, @Body InquiryCommentData inquiryCommentData);
}

package com.example.spotapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotapp.dto.InquiryData;
import com.example.spotapp.dto.InquiryListResponse;
import com.example.spotapp.retrofit.Interface.InquiryRetrofit;
import com.example.spotapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InquiryListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry_list);
        getInquirys();
        


        //testGet();
    }

    void testGet() {
        Retrofit retrofit = RetrofitClient.getClient();
        InquiryRetrofit inquiryRetrofit = retrofit.create(InquiryRetrofit.class);
        Call<InquiryListResponse> inquiry = inquiryRetrofit.getInquiry(11L);
        inquiry.enqueue(new Callback<InquiryListResponse>() {
            @Override
            public void onResponse(Call<InquiryListResponse> call, Response<InquiryListResponse> response) {
                 if(response.isSuccessful()) {
                    InquiryListResponse listResponse = response.body();
                    String status = listResponse.getStatus();
                    String message = listResponse.getMessage();
                    List<InquiryData> data = listResponse.getData();
                    System.out.println("status = " + status);
                    System.out.println("data = " + data);
                    System.out.println("message = " + message);
                } else {
                    System.out.println("FAIL!@!@!");
                }
            }

            @Override
            public void onFailure(Call<InquiryListResponse> call, Throwable t) {
                System.out.println("t = " + t);
            }
        });
    }

    void getInquirys() {
        Retrofit retrofit = RetrofitClient.getClient();
        InquiryRetrofit inquiryRetrofit = retrofit.create(InquiryRetrofit.class);
        Call<InquiryListResponse> inquiry = inquiryRetrofit.getInquirys();
        inquiry.enqueue(new Callback<InquiryListResponse>() {
            @Override
            public void onResponse(Call<InquiryListResponse> call, Response<InquiryListResponse> response) {
                if(response.isSuccessful()) {
                    InquiryListResponse listResponse = response.body();
                    String status = listResponse.getStatus();
                    String message = listResponse.getMessage();
                    List<InquiryData> data = listResponse.getData();
                    System.out.println("status = " + status);
                    for (InquiryData datum : data) {
                        System.out.println("datum = " + datum);
                    }
//                    System.out.println("data = " + data);
                    System.out.println("message = " + message);

                    RecyclerView recyclerView = findViewById(R.id.rv_inquiry);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InquiryListActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정
                    InquiryAdapter inquiryAdapter = new InquiryAdapter(data);
                    recyclerView.setAdapter(inquiryAdapter); // 어댑터 설정

                } else {
                    System.out.println("FAIL!@!@!");
                }
            }

            @Override
            public void onFailure(Call<InquiryListResponse> call, Throwable t) {
                System.out.println("t = " + t);
            }
        });
    }
}

package com.example.spotapp.inquiry;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotapp.R;
import com.example.spotapp.dto.CommonResponse;
import com.example.spotapp.dto.InquiryData;
import com.example.spotapp.retrofit.Interface.InquiryRetrofit;
import com.example.spotapp.retrofit.RetrofitClient;

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
        


    }


    void getInquirys() {
        Retrofit retrofit = RetrofitClient.getClient();
        InquiryRetrofit inquiryRetrofit = retrofit.create(InquiryRetrofit.class);
        Call<CommonResponse<List<InquiryData>>> inquiry = inquiryRetrofit.getInquirys();
        inquiry.enqueue(new Callback<CommonResponse<List<InquiryData>>>() {
            @Override
            public void onResponse(Call<CommonResponse<List<InquiryData>>> call, Response<CommonResponse<List<InquiryData>>> response) {
                if(response.isSuccessful()) {
                    CommonResponse<List<InquiryData>> listResponse = response.body();
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
            public void onFailure(Call<CommonResponse<List<InquiryData>>> call, Throwable t) {
                System.out.println("t = " + t);
            }
        });
    }
}

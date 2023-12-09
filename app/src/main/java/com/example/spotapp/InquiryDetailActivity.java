package com.example.spotapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotapp.dto.CommonResponse;
import com.example.spotapp.dto.InquiryCommentData;
import com.example.spotapp.dto.InquiryCommentsResponse;
import com.example.spotapp.dto.InquiryData;
import com.example.spotapp.dto.InquiryListResponse;
import com.example.spotapp.retrofit.Interface.InquiryRetrofit;
import com.example.spotapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InquiryDetailActivity extends AppCompatActivity {
    TextView detail_title, detail_name, detail_content, detail_regDate;
    String id, title, name, content, regDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry_detail_page);
        getInquiry();
        getComments();

        Button button = (Button) findViewById(R.id.inquiry_comment_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = ((EditText)findViewById(R.id.inquiry_comment_content)).getText().toString();
                String writer = ((EditText)(findViewById(R.id.inquiry_comment_writer))).getText().toString();
                InquiryCommentData inquiryCommentData = new InquiryCommentData();
                inquiryCommentData.setContent(content);
                inquiryCommentData.setName(writer);
                System.out.println("[제출 버튼이 눌러짐]");
                System.out.println("inquiryCommentData = " + inquiryCommentData);
                postComment(inquiryCommentData);
            }
        });
    }

    public void getInquiry() {
        detail_title = findViewById(R.id.inquiry_detail_title);
        detail_name = findViewById(R.id.inquiry_detail_name);
        detail_content = findViewById(R.id.inquiry_detail_content);
        detail_regDate = findViewById(R.id.inquiry_detail_regDate);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        title = intent.getExtras().getString("title");
        name = intent.getExtras().getString("name");
        content = intent.getExtras().getString("content");
        regDate = intent.getExtras().getString("regDate");

        detail_title.setText(title);
        detail_name.setText(name);
        detail_regDate.setText(regDate);
        detail_content.setText(content);
    }

    public void getComments() {
        Retrofit retrofit = RetrofitClient.getClient();
        InquiryRetrofit inquiryRetrofit = retrofit.create(InquiryRetrofit.class);
        Call<InquiryCommentsResponse> inquiry = inquiryRetrofit.getComments(Long.parseLong(id));

        inquiry.enqueue(new Callback<InquiryCommentsResponse>() {
            @Override
            public void onResponse(Call<InquiryCommentsResponse> call, Response<InquiryCommentsResponse> response) {
                if(response.isSuccessful()) {
                    InquiryCommentsResponse commentsResponse = response.body();
                    String status = commentsResponse.getStatus();
                    List<InquiryCommentData> data = commentsResponse.getData();
                    String message = commentsResponse.getMessage();


                    System.out.println("status = " + status);
                    for (InquiryCommentData datum : data) {
                        System.out.println("datum = " + datum);
                    }
                    System.out.println("message = " + message);

                    RecyclerView recyclerView = findViewById(R.id.rv_comment);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InquiryDetailActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정
                    InquiryCommentAdapter inquiryCommentAdapter = new InquiryCommentAdapter(data);
                    recyclerView.setAdapter(inquiryCommentAdapter); // 어댑터 설정

                } else {
                    System.out.println("FAIL!@!@!");
                }
            }

            @Override
            public void onFailure(Call<InquiryCommentsResponse> call, Throwable t) {
                System.out.println("t = " + t);
            }
        });



    }

    public void postComment(InquiryCommentData inquiryCommentData) {
        Retrofit retrofit = RetrofitClient.getClient();
        InquiryRetrofit inquiryRetrofit = retrofit.create(InquiryRetrofit.class);
        Call<CommonResponse<InquiryCommentData>> inquiry = inquiryRetrofit.addComment(Long.parseLong(id), inquiryCommentData);

        inquiry.enqueue(new Callback<CommonResponse<InquiryCommentData>>() {
            @Override
            public void onResponse(Call<CommonResponse<InquiryCommentData>> call, Response<CommonResponse<InquiryCommentData>> response) {
                if(response.isSuccessful()) {
                    CommonResponse commonResponse = response.body();
                    String status = commonResponse.getStatus();
                    InquiryCommentData data = (InquiryCommentData) commonResponse.getData();
                    String message = commonResponse.getMessage();


                    System.out.println("status = " + status);
                    System.out.println("data = " + data);
                    System.out.println("message = " + message);

                } else {
                    System.out.println("FAIL!@!@!");
                }
            }

            @Override
            public void onFailure(Call<CommonResponse<InquiryCommentData>> call, Throwable t) {
                System.out.println("t = " + t);
            }
        });
    }

}

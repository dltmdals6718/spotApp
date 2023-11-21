package com.example.spotapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotapp.dto.Inquiry;
import com.example.spotapp.retrofit.Interface.InquiryRetrofit;
import com.example.spotapp.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InquiryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry_page);

        Button inquirySubmit = (Button) findViewById(R.id.inquirySubmit);
        inquirySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title =((EditText) findViewById(R.id.inquiryTitle)).getText().toString();
                String name =((EditText) findViewById(R.id.inquiryName)).getText().toString();
                String content =((EditText) findViewById(R.id.inquiryContent)).getText().toString();
                addInquiry(title, name, content);

                Toast.makeText(getApplicationContext(), "문의사항이 접수되었습니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addInquiry(String title, String name, String content) {
        Inquiry inquiry = new Inquiry(title, name, content);
        Retrofit retrofit = RetrofitClient.getClient();
        InquiryRetrofit inquiryRetrofit = retrofit.create(InquiryRetrofit.class);
        Call<Inquiry> inquiryCall = inquiryRetrofit.addInquiry(inquiry);
        inquiryCall.enqueue(new Callback<Inquiry>() {
            @Override
            public void onResponse(Call<Inquiry> call, Response<Inquiry> response) {
                if(response.isSuccessful()) {
                    Inquiry inquiry = response.body();
                    Log.d("태그가 뭐시여?", inquiry.toString());
                } else {
                    Log.d("태그가 뭐지?", "실패");
                }
            }

            @Override
            public void onFailure(Call<Inquiry> call, Throwable t) {
                Log.d("이건 뭔 태그여?", t.getMessage());
            }
        });
    }

}

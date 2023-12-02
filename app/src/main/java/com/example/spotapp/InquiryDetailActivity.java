package com.example.spotapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InquiryDetailActivity extends AppCompatActivity {
    TextView detail_title, detail_name, detail_content, detail_regDate;
    String id, title, name, content, regDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry_detail_page);

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
}

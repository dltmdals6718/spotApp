package com.example.spotapp.inquiry;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotapp.MainActivity;
import com.example.spotapp.R;
import com.example.spotapp.dto.Inquiry;
import com.example.spotapp.retrofit.Interface.InquiryRetrofit;
import com.example.spotapp.retrofit.RetrofitClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InquiryActivity extends AppCompatActivity {

    private List<MultipartBody.Part> files = new ArrayList<>();
    private List<Uri> uriList = new ArrayList<>();
    RecyclerView recyclerView;
    MultiImageAdapter multiImageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry_page);
        recyclerView = findViewById(R.id.rv_inquiryImageUpload);

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

        Button imageSubmit = (Button) findViewById(R.id.inquiryImageUploadBtn);
        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                ClipData clipData = result.getData().getClipData();

                if(clipData == null) {
                    System.out.println("이미지 하나 선택");

                    Uri uri = result.getData().getData();
                    System.out.println("uri = " + uri);
                    System.out.println("getRealPathFromURI(imageUri) = " + getRealPathFromURI(uri));
                    uploadImageToServer(uri);
                    uriList.add(uri);
                } else {
                    System.out.println("이미지 여러개 선택");
                    for(int i=0; i<clipData.getItemCount(); i++) {
                        Uri uri = clipData.getItemAt(i).getUri();
                        System.out.println("uri = " + uri.getPath());
                        System.out.println("getRealPathFromURI(imageUri) = " + getRealPathFromURI(uri));
                        uploadImageToServer(uri);
                        uriList.add(uri);
                    }
                }

                multiImageAdapter = new MultiImageAdapter(uriList, getApplicationContext());
                recyclerView.setAdapter(multiImageAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));






//                Uri imageUri = result.getData().getData();
//                // 여기서 서버로 이미지를 업로드하는 메서드를 호출합니다
//                uploadImageToServer(imageUri);

            }
        });
        imageSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("imageSubmit Click!");

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityResult.launch(intent);
            }
        });
    }

    public void uploadImageToServer(Uri imageUri) {
        System.out.println("imageUri = " + imageUri);
        System.out.println("getRealPathFromURI(imageUri) = " + getRealPathFromURI(imageUri));
        File file = new File(getRealPathFromURI(imageUri));
        RequestBody requestBody = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
        files.add(part);
    }

    // Uri에서 실제 파일 경로 가져오기
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void addInquiry(String title, String name, String content) {
        Inquiry inquiry = new Inquiry(title, name, content);
        Retrofit retrofit = RetrofitClient.getClient();
        InquiryRetrofit inquiryRetrofit = retrofit.create(InquiryRetrofit.class);
        Call<Inquiry> inquiryCall = inquiryRetrofit.addInquiry(inquiry, files);

        if(files != null) {
            for (MultipartBody.Part file : files) {
                System.out.println("file = " + file);
            }
        }
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

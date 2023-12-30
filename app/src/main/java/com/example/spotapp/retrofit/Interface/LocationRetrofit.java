package com.example.spotapp.retrofit.Interface;

import com.example.spotapp.dto.CommonResponse;
import com.example.spotapp.dto.location.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationRetrofit {

    @GET("/locations")
    Call<CommonResponse<List<Location>>> getLocations(@Query("latitude") Double latitude, @Query("longitude") Double longitude);
}

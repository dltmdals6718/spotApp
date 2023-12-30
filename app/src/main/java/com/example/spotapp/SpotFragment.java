package com.example.spotapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotapp.dto.CommonResponse;
import com.example.spotapp.dto.location.Location;
import com.example.spotapp.retrofit.Interface.LocationRetrofit;
import com.example.spotapp.retrofit.RetrofitClient;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelLayerOptions;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;
import com.kakao.vectormap.label.TrackingManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SpotFragment extends Fragment {

    private static double longitude;
    private static double latitude;
    private static Label curLabel;
    private static KakaoMap myKakaoMap;

    public static KakaoMap getMyKakaoMap() {
        return myKakaoMap;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        SpotFragment.longitude = longitude;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        SpotFragment.latitude = latitude;
    }

    public static Label getCurLabel() {
        return curLabel;
    }

    public static void setCurLabel(Label curLabel) {
        SpotFragment.curLabel = curLabel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spot, container, false);
        MapView mapView = view.findViewById(R.id.map_view);
        mapView.start(new MapLifeCycleCallback() {
            @Override
            public void onMapDestroy() {
                // 지도 API 가 정상적으로 종료될 때 호출됨
            }

            @Override
            public void onMapError(Exception error) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }
        }, new KakaoMapReadyCallback() {
            @Override
            public void onMapReady(KakaoMap kakaoMap) {
                // 인증 후 API 가 정상적으로 실행될 때 호출됨


                myKakaoMap = kakaoMap;

                // 라벨 생성
                LabelStyles styles = kakaoMap.getLabelManager()
                        .addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.cur_pos)));
                LabelOptions options = LabelOptions.from(LatLng.from(latitude,longitude))
                        .setStyles(styles);
                LabelLayer layer = kakaoMap.getLabelManager().getLayer();
                Label label = layer.addLabel(options);
                curLabel = label;

                // 라벨 추적
                TrackingManager trackingManager = kakaoMap.getTrackingManager();
                trackingManager.startTracking(label);
            }

            @Override
            public LatLng getPosition() {
                // 지도 시작 시 위치 좌표를 설정
                return LatLng.from(35.24589, 128.6920);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    static void updateLocationLabel(Double latitude, Double longitude) {

        // locationLayer에 존재하는 모든 라벨 삭제
        if(myKakaoMap.getLabelManager().getLayer("locationLayer") != null) {
            myKakaoMap.getLabelManager().getLayer("locationLayer").removeAll();
        }

        // locationLayer 추가
        myKakaoMap.getLabelManager().addLayer(LabelLayerOptions.from("locationLayer"));

        // location 라벨들 찍기
        LabelStyles locationStyles = myKakaoMap.getLabelManager()
                .addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.blue_marker)));
        LabelLayer locationLayer = myKakaoMap.getLabelManager().getLayer("locationLayer");


        LabelOptions locationOptions1 = LabelOptions.from(LatLng.from(latitude-0.01,longitude-0.01))
                .setStyles(locationStyles);
        LabelOptions locationOptions2 = LabelOptions.from(LatLng.from(latitude-0.01,longitude+0.01))
                .setStyles(locationStyles);
        LabelOptions locationOptions3 = LabelOptions.from(LatLng.from(latitude+0.01,longitude-0.01))
                .setStyles(locationStyles);
        LabelOptions locationOptions4 = LabelOptions.from(LatLng.from(latitude+0.01,longitude+0.01))
                .setStyles(locationStyles);

        Label locationLabel1 = locationLayer.addLabel(locationOptions1);
        Label locationLabel2 = locationLayer.addLabel(locationOptions2);
        Label locationLabel3 = locationLayer.addLabel(locationOptions3);
        Label locationLabel4 = locationLayer.addLabel(locationOptions4);

        Retrofit retrofit = RetrofitClient.getClient();
        LocationRetrofit locationRetrofit = retrofit.create(LocationRetrofit.class);
        Call<CommonResponse<List<Location>>> locations = locationRetrofit.getLocations(SpotFragment.getLatitude(), SpotFragment.getLongitude());
        locations.enqueue(new Callback<CommonResponse<List<Location>>>() {
            @Override
            public void onResponse(Call<CommonResponse<List<Location>>> call, Response<CommonResponse<List<Location>>> response) {
                if(response.isSuccessful()) {
                    CommonResponse<List<Location>> commonResponse = response.body();
                    List<Location> data = commonResponse.getData();
                    for (Location datum : data) {
                        System.out.println("datum = " + datum);
                    }
                } else {
                    System.out.println("FAIL");
                }
            }

            @Override
            public void onFailure(Call<CommonResponse<List<Location>>> call, Throwable t) {
                System.out.println("Fail = " + t);
            }
        });

    }

}
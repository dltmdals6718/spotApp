package com.example.spotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;

public class MainActivity extends AppCompatActivity {

    Fragment homeFragment;
    Fragment spotFragment;
    Fragment settingFragment;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void initLayout() {


        homeFragment = new HomeFragment();
        spotFragment = new SpotFragment();
        settingFragment = new SettingFragment();
        switchFragment(homeFragment);
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    switchFragment(homeFragment);
                    return true;
                } else if (itemId == R.id.spot) {
                    switchFragment(spotFragment);
                    return true;
                } else if (itemId == R.id.setting) {
                    switchFragment(settingFragment);
                    return true;
                }
                return false;
            }
        });

        MapView mapView = findViewById(R.id.map_view);
        System.out.println("mapView = " + mapView);
//        mapView.start(new MapLifeCycleCallback() {
//            @Override
//            public void onMapDestroy() {
//                // 지도 API 가 정상적으로 종료될 때 호출됨
//            }
//
//            @Override
//            public void onMapError(Exception error) {
//                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
//            }
//        }, new KakaoMapReadyCallback() {
//            @Override
//            public void onMapReady(KakaoMap kakaoMap) {
//                // 인증 후 API 가 정상적으로 실행될 때 호출됨
//                LabelStyles styles = kakaoMap.getLabelManager()
//                        .addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.blue_marker)));
//
//                LabelOptions options = LabelOptions.from(LatLng.from(35.24589,128.6920))
//                        .setStyles(styles);
//
//                LabelLayer layer = kakaoMap.getLabelManager().getLayer();
//
//                Label label = layer.addLabel(options);
//            }
//
//            @Override
//            public LatLng getPosition() {
//                // 지도 시작 시 위치 좌표를 설정
//                return LatLng.from(35.24589, 128.6920);
//            }
//        });
    }
}
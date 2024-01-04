package com.example.spotapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.spotapp.inquiry.InquiryActivity;
import com.example.spotapp.inquiry.InquiryListActivity;
import com.kakao.sdk.auth.AuthApiClient;
import com.kakao.sdk.user.UserApiClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // 문의하기 버튼 클릭시 InquiryActivity로 변경
        Button button = view.findViewById(R.id.inquiryButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InquiryActivity.class);
                startActivity(intent);
            }
        });

        // 문의내역 버튼 클릭시 InquiryListActivity로 변경
        Button inquiryListButton = view.findViewById(R.id.inquiryListButton);
        inquiryListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InquiryListActivity.class);
                startActivity(intent);
            }
        });

        // 카카오 로그인 버튼 클릭시
        Button kakaoLoginButton = view.findViewById(R.id.kakaoLoginButton);
        kakaoLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("카카오 로그인 버튼 클릭");
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(getActivity())) {
                    login();
                } else {
                    accountLogin();
                }
            }
        });

        // 카카오 로그아웃 버튼 클릭시
        Button kakaoLogoutButton = view.findViewById(R.id.kakaoLogoutButton);
        kakaoLogoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                logout();

            }
        });
    }

    void login() {
        System.out.println("KakaoApp Login");
        UserApiClient.getInstance().loginWithKakaoTalk(getActivity(), ((oAuthToken, throwable) -> {
            if (throwable != null) {
                System.out.println("로그인 실패");
            } else if (oAuthToken != null) {
                System.out.println("로그인 성공 : " + oAuthToken);
                getTokenInfo();
                getUserInfo();
            }
            System.out.println("EXIT");
            return null;
        }));
    }

    void logout() {
        UserApiClient.getInstance().logout(throwable -> {
            if(throwable != null) {
                System.out.println("로그아웃 실패(토큰삭제완료) : " + throwable);
            } else {
                System.out.println("로그아웃 성공(토큰삭제완료)");
            }
            return null;
        });
    }

    void accountLogin() {
        System.out.println("account Login");
        UserApiClient.getInstance().loginWithKakaoAccount(getActivity(), (oAuthToken, throwable) -> {
            if (throwable != null) {
                System.out.println("로그인 실패");
            } else if (oAuthToken != null) {
                System.out.println("로그인 성공 : " + oAuthToken);
                getTokenInfo();
                getUserInfo();
            }
            return null;
        });
    }

    static void getUserInfo() {
        System.out.println("[SettingFragment.getUserInfo]");
        UserApiClient.getInstance().me(((user, throwable) -> {
            if(throwable != null) {
                System.out.println("getUserInfo Fail : " + throwable);
            } else {
                System.out.println("getUserInfo Success");
                System.out.println("user Id = " + user.getId());
                System.out.println("user Account = " + user.getKakaoAccount());
            }
            return null;
        }));
    }

    static void getTokenInfo() {
        System.out.println("[SettingFragment.getTokenInfo]");
        UserApiClient.getInstance().accessTokenInfo((accessTokenInfo, throwable) -> {
            if(throwable != null) {
                System.out.println("getTokenInfo Fail : " + throwable);
            } else {
                System.out.println("getTokenInfo Success");
                System.out.println("accessTokenInfo Id = " + accessTokenInfo.getId());
                System.out.println("accessTokenInfo.getExpiresIn() = " + accessTokenInfo.getExpiresIn());;
            }

            return null;
        });
    }

    static void checkToken() {
        System.out.println("[SettingFragment.checkToken]");
        if(AuthApiClient.getInstance().hasToken()) {
            UserApiClient.getInstance().accessTokenInfo((accessTokenInfo, throwable) -> {
                if(throwable != null) {
                    System.out.println("[checkToken] 로그인 필요 또는 에러");
                } else {
                    System.out.println("[checkToken] 토큰 유효성 체크 성공(필요 시 토큰 갱신됨)");
                }
                return null;
            });
        } else {
            System.out.println("[checkToken] 로그인 필요.");
        }
     }


}
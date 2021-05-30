package com.mentenseoul.samplecontest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    ImageButton imageButtons[] = new ImageButton[10];
    ImageView imageView;
    TextView userText;
    TextView pointView;
    TextView discountView;
    ImageView medalView;
    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;
    Button logoutButton;
    int point;
    int imageCount = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userText = view.findViewById(R.id.userText);
        medalView = view.findViewById(R.id.medalView);
        pointView = view.findViewById(R.id.pointView);
        discountView = view.findViewById(R.id.discountView);
        logoutButton = view.findViewById(R.id.logoutButton);
        imageButtons[0] = view.findViewById(R.id.imageButton1);
        imageButtons[1] = view.findViewById(R.id.imageButton2);
        imageButtons[2] = view.findViewById(R.id.imageButton3);
        imageButtons[3] = view.findViewById(R.id.imageButton4);
        imageButtons[4] = view.findViewById(R.id.imageButton5);
        imageButtons[5] = view.findViewById(R.id.imageButton6);
        imageButtons[6] = view.findViewById(R.id.imageButton7);
        imageButtons[7] = view.findViewById(R.id.imageButton8);
        imageButtons[8] = view.findViewById(R.id.imageButton9);
        imageButtons[9] = view.findViewById(R.id.imageButton10);
        userPageResponse();
        logout();
        imageView = view.findViewById(R.id.imageView_icon);


    }
    private void userPageResponse() {
        String token = SaveSharedPreference.getUserName(getContext());

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        Call<ArrayList<UserResponse>> call = retrofitAPI.getUserResponse(token);

        call.enqueue(new Callback<ArrayList<UserResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UserResponse>> call, Response<ArrayList<UserResponse>> response) {
                if(response.isSuccessful()){

                    userText.setText(response.body().get(0).getUsername() + " 님, 안녕하세요");
                    point = response.body().get(0).getPoint();
                    pointView.setText("" + response.body().get(0).getPoint());
                    discountView.setText("" + response.body().get(0).getDiscount());
                    isImagePoint(point);
                    if(response.body().get(0).getRank().equals("BRONZE")){
                        medalView.setImageResource(R.drawable.bronzemedal);
                    } else if(response.body().get(0).getRank().equals("SILVER")){
                        medalView.setImageResource(R.drawable.silvermedal);
                    } else if(response.body().get(0).getRank().equals("GOLD")){
                        medalView.setImageResource(R.drawable.goldmedal);
                    } else{
                        medalView.setImageResource(R.drawable.polarbear);
                    }
                } else {
                    try {
                        Log.d("error", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserResponse>> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });

    }
    private void logout() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveSharedPreference().clearUserName(getContext());
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void isImagePoint(int point) {
        if(point <= 3){
            imageCount = 1;
        } else if (point <= 6){
            imageCount = 2;
        } else if (point <= 9){
            imageCount = 3;
        }else if (point <= 12){
            imageCount = 4;
        }else if (point <= 15){
            imageCount = 5;
        }else if (point <= 18){
            imageCount = 6;
        }else if (point <= 21){
            imageCount = 7;
        }else if (point <= 24){
            imageCount = 8;
        }else if (point <= 27){
            imageCount = 9;
        }else{
            imageCount = 10;
        }
        for(int i = 0; i< imageCount; i++) {
            int img = i;
            imageButtons[i].setBackgroundColor(Color.parseColor("#FAF9FE"));
            imageButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageButtons[img].getDrawable();
                    Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                    imageView.setImageBitmap(tmpBitmap);
                }
            });
        }
    }
}
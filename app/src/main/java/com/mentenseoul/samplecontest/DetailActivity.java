package com.mentenseoul.samplecontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    Button button;
    public static Activity activity;

    TextView modeltext;
    TextView listText;
    TextView companyText;
    TextView rankText;

    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;

    XmlData xmlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        activity = DetailActivity.this;

        button = findViewById(R.id.button);
        modeltext = findViewById(R.id.modelText);
        listText = findViewById(R.id.listText);
        companyText = findViewById(R.id.companyText);
        rankText = findViewById(R.id.rankText);

        Intent intent = getIntent();
        xmlData = (XmlData) intent.getSerializableExtra("list");

        modeltext.setText(xmlData.getModel());
        listText.setText(xmlData.getName());
        companyText.setText(xmlData.getCompany());
        rankText.setText(xmlData.getRank());
        if(xmlData.getModel().equals("없음")){
            button.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                purchaseResponse();
                Intent intent = new Intent(getApplicationContext(), ButtonResultActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("data", xmlData.getRank());
                intent.putExtra("myBundle", bundle);
                startActivity(intent);


            }
        });



    }

    private void purchaseResponse() {
        String token = SaveSharedPreference.getUserName(getApplicationContext());

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        PurchaseRequest purchaseRequest = new PurchaseRequest(xmlData.getCompany(), xmlData.getName(), xmlData.getModel(), xmlData.getRank());

        retrofitAPI.getPurchaseResponse(token, purchaseRequest).enqueue(new Callback<PurchaseResponse>() {
            @Override
            public void onResponse(Call<PurchaseResponse> call, Response<PurchaseResponse> response) {

                if (response.isSuccessful()
                        && response.body().getStatus().toString().equals("200")
                        && response.body().getResponseMessage().equals("구매 성공")){
                    Toast.makeText(DetailActivity.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        Log.d("error", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            //통신 실패
            @Override
            public void onFailure(Call<PurchaseResponse> call, Throwable t){
                Log.d("fail", t.getMessage());

            }
        });
    }


}

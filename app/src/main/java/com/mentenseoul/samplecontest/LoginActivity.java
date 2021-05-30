package com.mentenseoul.samplecontest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText idText;
    EditText passwordText;
    Button loginButton;

    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;

    TextView signText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        signText = findViewById(R.id.signText);
        signText.setOnClickListener(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String pw = passwordText.getText().toString();

                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null)
                {
                    Toast.makeText(LoginActivity.this, "로그인 정보를 입력 바랍니다.", Toast.LENGTH_SHORT).show();

                } else {
//                    로그인 통신
                    LoginResponse();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signText:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void LoginResponse() {
        String userID = idText.getText().toString().trim();
        String userPassword = passwordText.getText().toString().trim();
        //loginRequest에 사용자가 입력한 id와 pw를 저장
        LoginRequest loginRequest = new LoginRequest(userID, userPassword);

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() // 로그인 성공
                        && response.body().getStatus().toString().equals("200")
                        && response.body().getResponseMessage().equals("로그인 성공")){
                    Toast.makeText(LoginActivity.this, "로그인 성공!!", Toast.LENGTH_SHORT).show();
                    //화면 전환
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    SaveSharedPreference saveSharedPreference = new SaveSharedPreference();
                    saveSharedPreference.setUserName(getApplicationContext(), response.body().getData().getJwt());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else { // 로그인 실패
                    try {
                        errorMessage(response.errorBody().string(), RestError.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            //통신 실패
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t){
                Log.d("fail", t.getMessage());

            }
        });
    }
    public void errorMessage(String errorBody, Class errorClass) {
        Gson gson = new Gson();
        RestError restError = null;
        restError = gson.fromJson(
                errorBody,
                (Type) errorClass);
        Log.d("error", restError.getResponseMessage());
        Toast.makeText(this, restError.getResponseMessage(), Toast.LENGTH_SHORT).show();

    }
}
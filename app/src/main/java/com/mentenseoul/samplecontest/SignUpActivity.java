package com.mentenseoul.samplecontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText idText;
    EditText nameText;
    EditText passwordText;
    EditText passwordCheckText;
    Button signUpButton;
    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        idText = findViewById(R.id.idText);
        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
        passwordCheckText = findViewById(R.id.passwordCheckText);
        signUpButton = findViewById(R.id.loginButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String pw = passwordText.getText().toString();
                String name = nameText.getText().toString();
                String pwC = passwordCheckText.getText().toString();

                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
//                    builder.setTitle("알림")
//                            .setMessage("회원가입 정보를 입력바랍니다.")
//                            .setPositiveButton("확인", null)
//                            .create()
//                            .show();
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
                    Toast.makeText(SignUpActivity.this, "회원가입 정보를 입력바랍니다.", Toast.LENGTH_SHORT).show();

                } else {
//                    로그인 통신
                    if(!pw.equals(pwC)){
                        Toast.makeText(SignUpActivity.this, "비밀번호가 서로 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        JoinResponse();
                    }
                    
                }
            }
        });
    }
    public void JoinResponse() {
        String userID = idText.getText().toString().trim();
        String userPassword = passwordText.getText().toString().trim();
        String name = nameText.getText().toString().trim();
        //loginRequest에 사용자가 입력한 id와 pw를 저장
        JoinRequest joinRequest = new JoinRequest(userID, userPassword, name);

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getJoinResponse(joinRequest).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {

                if (response.isSuccessful()
                        && response.body().getStatus().toString().equals("200")){
//
                    Log.d("join", "회원 가입 성공");
                    Toast.makeText(SignUpActivity.this, "회원 가입 성공!", Toast.LENGTH_SHORT).show();
                    finish();
                } else { // 로그인 실패
                    try {
                        errorMessage(response.errorBody().string(), RestError.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
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
        Toast.makeText(getApplicationContext(), restError.getResponseMessage(), Toast.LENGTH_SHORT).show();

    }
}
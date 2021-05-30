package com.mentenseoul.samplecontest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizFragment extends Fragment {

    TextView titleText;
    Button submitButton;
    RadioGroup radioGroup;
    RadioButton yesButton;
    RadioButton noButton;

    String id;
    String ans;

    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleText = view.findViewById(R.id.titleText);
        submitButton = view.findViewById(R.id.submitButton);
        radioGroup = view.findViewById(R.id.radioGroup);
        yesButton = view.findViewById(R.id.yesButton);
        noButton = view.findViewById(R.id.noButton);

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(yesButton.isChecked())
//            }
//        })


        quizResponse();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yesButton.isChecked()){
                    if(ans.equals("true")){
                        Toast.makeText(getContext(), "정답!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "오답ㅠㅠ", Toast.LENGTH_SHORT).show();
                    }
                } else if (noButton.isChecked()){
                    if(ans.equals("false")){
                        Toast.makeText(getContext(), "정답!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "오답ㅠㅠ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "답을 골라주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void quizResponse() {
        String token = SaveSharedPreference.getUserName(getContext());

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getQuizResponse(token).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {

                if (response.isSuccessful()
                        && response.body().getStatus().toString().equals("200")
                        && response.body().getResponseMessage().equals("퀴즈 출제 성공")){
                    titleText.setText(response.body().getData().getTitle());
                    id  = response.body().getData().getId().toString();
                    ans = response.body().getData().getAnswer().toString();

                } else {
                    try {
                        Log.d("error", response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            //통신 실패
            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t){
                Log.d("fail", t.getMessage());

            }
        });
    }
    private void answerResponse() {
        String token = SaveSharedPreference.getUserName(getContext());

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getAnswerResponse(token, "2", "true").enqueue(new Callback<AnswerResponse>() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful()){
                    Log.d("answer", "test");
                } else {
                    try {
                        Log.d("error", "message: " + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            //통신 실패
            @Override
            public void onFailure(Call<AnswerResponse> call, Throwable t){
                Log.d("fail", t.getMessage());

            }
        });
    }



}

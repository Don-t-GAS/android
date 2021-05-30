package com.mentenseoul.samplecontest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizAnswer extends Fragment {

    TextView answerText;
    Button okayButton;

    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        answerResponse();
    }
    */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_quiz_answer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        answerText = view.findViewById(R.id.answerText);
        okayButton = view.findViewById(R.id.okayButton);

        answerResponse();
    }

    private void answerResponse() {
        String token = SaveSharedPreference.getUserName(getContext());

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getQuizResponse(token).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {

                if (response.isSuccessful()){
                    answerText.setText(response.body().getData().getDescription());
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
            public void onFailure(Call<QuizResponse> call, Throwable t){
                Log.d("fail", t.getMessage());

            }
        });
    }
}
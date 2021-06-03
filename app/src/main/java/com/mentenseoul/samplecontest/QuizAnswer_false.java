package com.mentenseoul.samplecontest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizAnswer_false extends Fragment {

    TextView answerText;
    Button okayButton;

    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;

    String des;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_quiz_answer_false, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        answerText = view.findViewById(R.id.answerText);
        okayButton = view.findViewById(R.id.okayButton);

        Bundle bundle = getArguments();

        if(bundle != null){
            des = bundle.getString("des");
            answerText.setText(des);
        }

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                QuizFragment quizFragment = new QuizFragment();
                transaction.replace(R.id.content_layout, quizFragment);
                transaction.commit();
            }
        });
    }
}
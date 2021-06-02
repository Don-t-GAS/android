package com.mentenseoul.samplecontest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    ArrayList<Integer> jsonList = new ArrayList<>(); // ArrayList 선언
    ArrayList<String> labelList = new ArrayList<>(); // ArrayList 선언
    View mView;

    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;

    //recyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLyaoutManager;
    RecyclerView.Adapter mAdapter;

    List<Data4> arrayList = new ArrayList<>();

    ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        detailResponse();
        countsResponse();
        init();

        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;



        //modelName, String name, String rank, String time


    }
    public void init(){

        mRecyclerView = rootView.findViewById(R.id.recyclerView);

        jsonList.add(10);
        jsonList.add(10);
        jsonList.add(10);
        jsonList.add(10);
        jsonList.add(10);
        labelList.clear();
        labelList.add("1등급");
        labelList.add("2등급");
        labelList.add("3등급");
        labelList.add("4등급");
        labelList.add("5등급");

    }
    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {
        // BarChart 메소드
        BarChart barChart = (BarChart)mView.findViewById(R.id.chart);
        barChart.getAxisRight().setAxisMaxValue(10);
        barChart.getAxisLeft().setAxisMaxValue(10);
        barChart.setTouchEnabled(false);
        barChart.setBorderColor(Color.YELLOW);

        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0; i < valList.size();i++){
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        BarDataSet depenses = new BarDataSet(entries, "개수"); // 변수로 받아서 넣어줘도 됨
        depenses.setAxisDependency(YAxis.AxisDependency.RIGHT);
        barChart.setDescription(" ");

        ArrayList<String> labels = new ArrayList<String>();
        for(int i=0; i < labelList.size(); i++){
            labels.add((String) labelList.get(i));
        }

        BarData data = new BarData(labels,depenses);
        depenses.setColors(new int[]{ColorTemplate.rgb("#FBEAFF"),ColorTemplate.rgb("#EEA8FF"),
                ColorTemplate.rgb("#CA65E2"),ColorTemplate.rgb("#86269C"),ColorTemplate.rgb("#4F175C")});
        depenses.setHighLightColor(ColorTemplate.rgb("#00FFD0"));

        barChart.setData(data);
        barChart.animateXY(0,1500);
        barChart.invalidate();
    }
    public void countsResponse() {
        String token = SaveSharedPreference.getUserName(getContext());
        //loginRequest에 사용자가 입력한 id와 pw를 저장

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getCounts(token).enqueue(new Callback<CountsResponse>() {
            @Override
            public void onResponse(Call<CountsResponse> call, Response<CountsResponse> response) {

                if (response.isSuccessful()
                        && response.body().getStatus().toString().equals("200")
                        && response.body().getResponseMessage().equals("등급별 갯수 조회 성공")){

                    jsonList.clear();
                    jsonList.add(response.body().getData().getFisrtCnt());
                    jsonList.add(response.body().getData().getSecondCnt());
                    jsonList.add(response.body().getData().getThirdCnt());
                    jsonList.add(response.body().getData().getFourthCnt());
                    jsonList.add(response.body().getData().getFivethCnt());
                    BarChartGraph(labelList, jsonList);

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
            public void onFailure(Call<CountsResponse> call, Throwable t){
                Log.d("fail", t.getMessage());

            }
        });
    }

    public void detailResponse() {
        String token = SaveSharedPreference.getUserName(getContext());

        retrofitClient = retrofitClient.getInstance();
        retrofitAPI = retrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getPurchaseDetailsResponse(token).enqueue(new Callback<PurchaseDetailsResponse>() {
            @Override
            public void onResponse(Call<PurchaseDetailsResponse> call, Response<PurchaseDetailsResponse> response) {

                if (response.isSuccessful()
                        && response.body().getStatus().toString().equals("200")
                        && response.body().getResponseMessage().equals("조회 성공")){
                    arrayList = response.body().getData();
                    mRecyclerView.setHasFixedSize(true);
                    mAdapter = new SaveAdapter(arrayList);
                    mLyaoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(mLyaoutManager);
                    mRecyclerView.setAdapter(mAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
                    mRecyclerView.addItemDecoration(dividerItemDecoration);
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
            public void onFailure(Call<PurchaseDetailsResponse> call, Throwable t){
                Log.d("fail", t.getMessage());

            }
        });
    }


}
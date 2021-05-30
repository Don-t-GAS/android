package com.mentenseoul.samplecontest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @POST("/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);
    // getLoginResponse 함수로 LoginRequest.java에 정의해준 데이터들을 서버 Body에 보낸 후 LoginResponse로 데이터를 받겠다는 의미를 가집니다.

    @POST("/join")
    Call<JoinResponse> getJoinResponse(@Body JoinRequest joinRequest);

    @GET("/api/counts")
    Call<CountsResponse> getCounts(@Header("X-AUTH-TOKEN") String token);

    @POST("/api/purchase")
    Call<PurchaseResponse> getPurchaseResponse(@Header ("X-AUTH-TOKEN") String token, @Body PurchaseRequest purchaseRequest);

    @GET("/api/purchase")
    Call<PurchaseDetailsResponse> getPurchaseDetailsResponse(@Header ("X-AUTH-TOKEN") String token);

    @GET("/api/quiz")
    Call<QuizResponse> getQuizResponse(@Header("X-AUTH-TOKEN") String token);

    @GET("/api/quiz/check")
    Call<AnswerResponse> getAnswerResponse(@Header("X-AUTH-TOKEN") String token, @Query("id") String id, @Query("answer") String answer);

    @GET("/api/userpage")
    Call<ArrayList<UserResponse>> getUserResponse(@Header("X-AUTH-TOKEN") String token);
}



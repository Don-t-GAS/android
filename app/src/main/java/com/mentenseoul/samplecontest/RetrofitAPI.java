package com.mentenseoul.samplecontest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);
    // getLoginResponse 함수로 LoginRequest.java에 정의해준 데이터들을 서버 Body에 보낸 후 LoginResponse로 데이터를 받겠다는 의미를 가집니다.

    @POST("/join")
    Call<JoinResponse> getJoinResponse(@Body JoinRequest joinRequest);
}



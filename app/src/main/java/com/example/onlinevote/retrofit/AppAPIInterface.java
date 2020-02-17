package com.example.onlinevote.retrofit;

import com.example.onlinevote.models.Auth;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppAPIInterface {

    @FormUrlEncoded()
    @POST("verify/aadhar/")
    Call<Auth> verifyAadhar(@FieldMap HashMap<String, String> params);


    @FormUrlEncoded()
    @POST("verify/otp/{userId}")
    Call<Auth> verifyOTP(@Path("userId") int user, @FieldMap HashMap<String, String> params);


    @FormUrlEncoded()
    @POST("verify/photo/{userId}/")
    Call<Auth> verifyPhoto(@Path("userId") int user, @FieldMap HashMap<String, String> params);


}

package com.example.onlinevote.retrofit;

import com.example.onlinevote.models.Auth;
import com.example.onlinevote.models.CandidateList;
import com.example.onlinevote.models.Election;
import com.example.onlinevote.models.ResponseResult;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public  interface AppAPIInterface {

    @FormUrlEncoded()
    @POST("verify/aadhar/")
    Call<Auth> verifyAadhar(@FieldMap HashMap<String, String> params);


    @FormUrlEncoded()
    @POST("verify/otp/{userId}")
    Call<Auth> verifyOTP(@Path("userId") int user, @FieldMap HashMap<String, String> params);

    @Multipart
    @POST("verify/photo/{userId}")
    Call<Auth> verifyPhoto(@Path("userId") int user, @Part MultipartBody.Part part);

    @GET("election/list")
    Call<ArrayList<Election>> fetchElections();


    @GET("election/{electionId}")
    Call<ArrayList<CandidateList>> fetchCandidates(@Path("electionId") int electionId);

    @POST("voting/{electionId}")
    Call<ResponseResult> vote(@Path("electionId") int electionId);
}
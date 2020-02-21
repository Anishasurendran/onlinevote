package com.example.onlinevote.config;

import android.content.Context;
import android.util.Log;


import com.example.onlinevote.interfaces.RetrofitCallBack;
import com.example.onlinevote.models.Auth;
import com.example.onlinevote.models.Candidate;
import com.example.onlinevote.models.CandidateList;
import com.example.onlinevote.models.Election;
import com.example.onlinevote.retrofit.AppAPIInterface;
import com.example.onlinevote.retrofit.AppClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {
    public static DataManager dataManager = null;
    private AppAPIInterface appAPIInterface;
    private Context mContext;

    public static DataManager getDataManager() {
        if (dataManager == null)
            dataManager = new DataManager();

        return dataManager;
    }

    public void init(Context context) {
        appAPIInterface = AppClient.getAPiClient().create(AppAPIInterface.class);
        mContext = context;
    }

    public  void verifyAadhar(HashMap<String, String> aadhar, final RetrofitCallBack<Auth> retrofitCallBack){

        Call<Auth> responseCall = AppClient.getAPiClient().create(AppAPIInterface.class).verifyAadhar(aadhar);
        responseCall.enqueue((new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if(response.isSuccessful())
                    retrofitCallBack.Success(response.body());
                else
                    retrofitCallBack.Failure("Something went wrong");
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                retrofitCallBack.Failure("Something went wrong");
            }
        }));

    }


    public   void verifyOTP(int userId,HashMap<String, String> otp, final RetrofitCallBack<Auth> retrofitCallBack){

        Call<Auth> responseCall =  appAPIInterface.verifyOTP(userId, otp);
        responseCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if(response.isSuccessful())
                    retrofitCallBack.Success(response.body());
                else
                    retrofitCallBack.Failure("Something went wrong");
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {

                retrofitCallBack.Failure("Something went wrong");
            }
        });

    }

    public void verifyPhoto(int userId, MultipartBody.Part photo, final RetrofitCallBack<Auth> retrofitCallBack){
        Call<Auth> responseCall =   AppClient.getAPiClient().create(AppAPIInterface.class).verifyPhoto(userId, photo);
        responseCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if(response.isSuccessful())
                    retrofitCallBack.Success(response.body());
                else
                    retrofitCallBack.Failure("Something went wrong");
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                retrofitCallBack.Failure("Something went wrong");
            }
        });
    }


    public void fetchElections(final RetrofitCallBack<ArrayList<Election>> retrofitCallBack){
        Call<ArrayList<Election>> responseCall = appAPIInterface.fetchElections();
        responseCall.enqueue(new Callback<ArrayList<Election>>() {
            @Override
            public void onResponse(Call<ArrayList<Election>> call, Response<ArrayList<Election>> response) {
                if(response.isSuccessful())
                    retrofitCallBack.Success(response.body());
                else
                    retrofitCallBack.Failure("Something went wrong");
            }

            @Override
            public void onFailure(Call<ArrayList<Election>> call, Throwable t) {
                retrofitCallBack.Failure("Something went wrong");
            }
        });
    }

    public void fetchCandidates(int electionId, final RetrofitCallBack<ArrayList<CandidateList>> retrofitCallBack){
        Call<ArrayList<CandidateList>> responseCall = appAPIInterface.fetchCandidates(electionId);
        responseCall.enqueue(new Callback<ArrayList<CandidateList>>() {
            @Override
            public void onResponse(Call<ArrayList<CandidateList>> call, Response<ArrayList<CandidateList>> response) {
                if(response.isSuccessful())
                    retrofitCallBack.Success(response.body());
                else
                    retrofitCallBack.Failure("Something went wrong");

            }

            @Override
            public void onFailure(Call<ArrayList<CandidateList>> call, Throwable t) {
                retrofitCallBack.Failure("Something went wrong");
            }
        });
    }


}

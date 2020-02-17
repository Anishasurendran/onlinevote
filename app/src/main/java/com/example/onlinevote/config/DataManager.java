package com.example.onlinevote.config;

import android.content.Context;
import android.util.Log;


import com.example.onlinevote.interfaces.RetrofitCallBack;
import com.example.onlinevote.models.Auth;
import com.example.onlinevote.retrofit.AppAPIInterface;
import com.example.onlinevote.retrofit.AppClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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





    }

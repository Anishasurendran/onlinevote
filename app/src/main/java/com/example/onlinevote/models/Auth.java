package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Auth {

    @SerializedName("token")
    String accessToken;

    @SerializedName("phone")
    String phone;

    @SerializedName("user")
    int id;

    @SerializedName("success")
    boolean success;

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public  String getPhone(){ return  phone;}
    public  void setPhone(String phone) {this.phone = phone;}

    public int getId(){return  id;}
    public  void setId(int id) {this.id =  id;}

    public  boolean getEmail(){return  success;}
    public void setEmail(boolean success){this.success = success;}
}

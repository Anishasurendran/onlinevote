package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Auth {

    @SerializedName("token")
    String accessToken;

    @SerializedName("phone")
    String phone;

    @SerializedName("user")
    int id;


    @SerializedName("email")
    String email;

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

    public  String getEmail(){return  email;}
    public void setEmail(String email){this.email = email;}
}

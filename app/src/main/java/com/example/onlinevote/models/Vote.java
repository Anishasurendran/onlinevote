package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Vote {


    @SerializedName("name")
    Election name;

    @SerializedName("candidate")
    Candidate candidate;

    public String getName(){return  name;}
    public void setName(String name){this.name = name;}





}

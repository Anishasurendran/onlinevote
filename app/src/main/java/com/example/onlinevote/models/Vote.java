package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Vote {


    @SerializedName("name")
    Election name;

    @SerializedName("candidate")
    Candidate candidate;

    public Election getName(){return  name;}
    public void setName(Election name){this.name = name;}





}

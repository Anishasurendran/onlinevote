package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Election {

    @SerializedName("id")
    int id;

    @SerializedName("election_name")
    String election_name;

    @SerializedName("sdate")
    String election_sdate;

    @SerializedName("edate")
    String election_edate;

    @SerializedName("location")
    String location;

    @SerializedName("count")
    int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElectionName(){return election_name;}
    public void setElectionName(String election_name){this.election_name = election_name;}


    public String getSdate(){return election_sdate;}
    public void setSdate(String election_sdate){this.election_sdate = election_sdate;}


    public String getEdate(){return election_edate;}
    public void setEdate(String election_edate){this.election_name = election_edate;}


    public String getLocation(){return location;}
    public void setLocation(String location){this.election_name = location;}

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}

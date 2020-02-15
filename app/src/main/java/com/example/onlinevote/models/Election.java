package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Election {
    @SerializedName("electionname")
    String election_name;

    @SerializedName("sdate")
    String election_sdate;

    @SerializedName("edate")
    String election_edate;

    @SerializedName("location")
    String location;

    public String getElectionName(){return election_name;}
    public void setElectionName(String election_name){this.election_name = election_name;}


    public String getSdate(){return election_sdate;}
    public void setSdate(String election_sdate){this.election_sdate = election_sdate;}


    public String getEdate(){return election_edate;}
    public void setEdate(String election_edate){this.election_name = election_edate;}


    public String getLocation(){return location;}
    public void setLocation(String location){this.election_name = location;}

}

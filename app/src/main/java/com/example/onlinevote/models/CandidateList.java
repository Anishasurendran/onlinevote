package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class CandidateList {


    @SerializedName("id")
    int id;

    @SerializedName("candidate")
    Candidate candidate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}

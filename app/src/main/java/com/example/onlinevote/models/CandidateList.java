package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class CandidateList {


    @SerializedName("candidate")
    Candidate candidate;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}

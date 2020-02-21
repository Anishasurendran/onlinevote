package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Candidate {

    @SerializedName("candidate_name")
    String candidate_name;


    @SerializedName("gender")
    String gender;

    @SerializedName("cand_district")
    String cand_district;

    @SerializedName("cand_taluk")
    String cand_taluk;


    public String getName() {
        return candidate_name;
    }

    public void setName(String candidate_name) {
        this.candidate_name = candidate_name;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDistrict() {
        return cand_district;
    }

    public void setDistrict(String cand_district) {
        this.cand_district = cand_district;
    }

    public String getTaluk() {
        return cand_taluk;
    }

    public void setTaluk(String cand_taluk) {
        this.cand_taluk = cand_taluk;
    }

}


package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class Candidate {

    @SerializedName("name")
    String candidate_name;

    @SerializedName("address")
    String cand_address;

    @SerializedName("gender")
    String gender;

    @SerializedName("district")
    String cand_district;

    @SerializedName("taluk")
    String cand_taluk;

    @SerializedName("phone_number")
    String cand_phoneno;



    public String getName(){return candidate_name;}
    public void setName(String candidate_name){this.candidate_name = candidate_name;}

    public String getAddress(){return cand_address;}
    public void setAddress(String cand_address){this.cand_address = cand_address;}

    public String getGender(){return gender;}
    public void setGender(String gender){this.gender = gender;}

    public String getDistrict(){return cand_district;}
    public void setDistrict(String cand_district){this.cand_district = cand_district;}

    public String getTaluk(){return cand_taluk;}
    public void setTaluk(String cand_taluk){this.cand_taluk = cand_taluk;}

    public String getPhoneNumber(){return cand_phoneno;}
    public void setPhoneNumber(String cand_phoneno){this.cand_phoneno = cand_phoneno;}



















}

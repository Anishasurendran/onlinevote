package com.example.onlinevote.models;

import com.google.gson.annotations.SerializedName;

public class ResponseResult {

    @SerializedName("success")
    Boolean success;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}


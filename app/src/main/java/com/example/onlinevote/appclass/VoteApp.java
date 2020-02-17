package com.example.onlinevote.appclass;

import android.app.Application;

import com.example.onlinevote.config.DataManager;
import com.example.onlinevote.config.Session;


public class VoteApp extends Application {

    private  static final String API_KEY ="AIzaSyA3ELJ-2i9B_8M43IvGNkMuQWnlCOQfwaQ";
    @Override
    public void onCreate() {
        super.onCreate();
        Session.getSession(this);

        DataManager.getDataManager().init(this);

    }
}

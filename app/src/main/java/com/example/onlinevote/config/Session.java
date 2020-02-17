package com.example.onlinevote.config;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private static final String PREFERENCE = "Preference";

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static void getSession(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void setAccessToken(String token) {
        editor.putString("access_token", token).apply();
    }

    public static String getAccessToken() {
        return sharedPreferences.getString("access_token", "");
    }

    public static void setName(String name) {
        editor.putString("name", name).apply();
    }

    public static String getName() {
        return sharedPreferences.getString("name", "");
    }

    public  static void setPhone(String phone){editor.putString("phone", phone).apply();}
    public static  String getPhone(){return sharedPreferences.getString("phone", "");}



    public  static void setUserId(int userId){editor.putInt("user", userId).apply();}
    public static  int getUserId(){return sharedPreferences.getInt("user", 0);}



    public static void setUserVerification(boolean status) {
        editor.putBoolean("verification", status).apply();
    }

    public static boolean getUserVerification() {
        return sharedPreferences.getBoolean("verification", false);
    }

}

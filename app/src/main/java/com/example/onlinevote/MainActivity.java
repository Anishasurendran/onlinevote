package com.example.onlinevote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.example.onlinevote.config.DataManager;
import com.example.onlinevote.config.Session;
import com.example.onlinevote.interfaces.RetrofitCallBack;
import com.example.onlinevote.models.Auth;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText adharNum1, adharNum2, adharNum3;
    private Button login;

    private String adharPart1, adharPart2, adharPart3;
    public static final int MY_PERMISSIONS_GET_SMS = 1212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        adharNum1 = findViewById(R.id.adharnum1);
        adharNum2 = findViewById(R.id.adharnum2);
        adharNum3 =  findViewById(R.id.adharnum3);
        login=(Button)findViewById(R.id.logsubmit);


        adharNum1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adharPart1 = adharNum1.getText().toString();
                if (adharPart1.length() == 4)     //size as per your requirement
                {
                    adharNum2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        adharNum2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adharPart2 = adharNum2.getText().toString();
                if (adharPart2.length() == 4)     //size as per your requirement
                {
                    adharNum3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        adharNum3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adharPart3 = adharNum3.getText().toString();
                if (adharPart3.length() == 4)     //size as per your requirement
                {
                    adharNum3.clearFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        login=(Button)findViewById(R.id.logsubmit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String  adharNumber = adharPart1+adharPart2+adharPart3;
                    if(adharNumber.equals("")){
                        Toast.makeText(MainActivity.this,"Field cannot be empty",Toast.LENGTH_SHORT).show();

                    }else if (adharNumber.length()!=12) {

                        Toast.makeText(getApplicationContext(), "pls enter 12 digits", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        DataManager.getDataManager().verifyAadhar(aadharParams(adharNumber), new RetrofitCallBack<Auth>() {
                            @Override
                            public void Success(Auth data) {
                                Session.setPhone(data.getPhone());
                                Session.setUserId(data.getId());
                                DataManager.getDataManager().init(MainActivity.this);
                                startActivity(new Intent(MainActivity.this,OTPVerificationActivity.class));
                            }

                            @Override
                            public void Failure(String error) {
                                Toast.makeText(getApplicationContext(), "Something vent wrong", Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                    }

        });

        checkPermission();

    }

    private HashMap<String, String> aadharParams(String aadhar){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("aadhar", aadhar);
        return hashMap;
    }

    public boolean checkPermission()
    {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Receive sms permission is necessary to read otp !!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_GET_SMS);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_GET_SMS);
                }
                return false;

            } else {
                return true;
            }
        } else {
            return true;
        }

    }
}


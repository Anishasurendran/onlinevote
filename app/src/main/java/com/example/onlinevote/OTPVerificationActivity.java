package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinevote.config.Constants;
import com.example.onlinevote.config.DataManager;
import com.example.onlinevote.config.Session;
import com.example.onlinevote.interfaces.RetrofitCallBack;
import com.example.onlinevote.models.Auth;

import java.util.HashMap;

public class OTPVerificationActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    private boolean verifyCalled = false;
    private boolean broadcastReceivedFlag = false;
    private String userOTP = "";

    private EditText edtTxtOTP1, edtTxtOTP2, edtTxtOTP3, edtTxtOTP4;
    private TextView txtVwWrongNo;
    private TextView phoneTextView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        // Initialize components

        edtTxtOTP1 = (EditText) findViewById(R.id.edtTxtOTP1);
        edtTxtOTP2 = (EditText) findViewById(R.id.edtTxtOTP2);
        edtTxtOTP3 = (EditText) findViewById(R.id.edtTxtOTP3);
        edtTxtOTP4 = (EditText) findViewById(R.id.edtTxtOTP4);
        txtVwWrongNo = (TextView) findViewById(R.id.txtVwWrongNo);
        phoneTextView = findViewById(R.id.phoneNumber);
        backButton = findViewById(R.id.otpBackButton);

        String phone = Session.getPhone();
        if(phone.length() > 2){
            phoneTextView.setText(phone.substring(0, 2)+"****"+phone.substring(6,10));
        }



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //Edit text controllers
        edtTxtOTP1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP1.getText().toString().length() == 1)     //size as per your requirement
                {
                    edtTxtOTP2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        edtTxtOTP2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP2.getText().toString().length() == 1)     //size as per your requirement
                {
                    edtTxtOTP3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        edtTxtOTP3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP3.getText().toString().length() == 1)     //size as per your requirement
                {
                    edtTxtOTP4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        edtTxtOTP4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP4.getText().toString().length() == 1 )     //size as per your requirement
                {
                    verifyUserOTP(edtTxtOTP1.getText().toString()+edtTxtOTP2.getText().toString()+edtTxtOTP3.getText().toString()+edtTxtOTP4.getText().toString());
//                    startActivity(new Intent(OTPVerificationActivity.this, VerifyImageActivity.class));
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
    }



    private void verifyUserOTP(String otp) {
        DataManager.getDataManager().verifyOTP(Session.getUserId(),otpParmas(otp), new RetrofitCallBack<Auth>() {
            @Override
            public void Success(Auth data) {
                startActivity(new Intent(OTPVerificationActivity.this, VerifyImageActivity.class));
            }

            @Override
            public void Failure(String error) {

            }
        });
    }


    private HashMap<String, String> otpParmas(String aadhar){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("otp", aadhar);
        return hashMap;
    }



    @Override
    protected void onStart() {
        super.onStart();

        registerBroadcast();
    }


    private void registerBroadcast() {

        Log.d(Constants.APP_NAME, "registerBroadcast ");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (intent.getAction()) {
                    case Constants.SEND_SMS:
                        Log.d(Constants.APP_NAME, "otp Received: "+intent.getStringExtra("sign_otp"));
                        userOTP = intent.getStringExtra("sign_otp");
                        broadcastReceivedFlag = true;
                        setLoginOTP();
                        break;

                }

            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.SEND_SMS);
        registerReceiver(broadcastReceiver, filter);

    }

    private void setLoginOTP() {

        if (!TextUtils.isEmpty(userOTP) && userOTP.length() == 4) {

            for (int i = 0; i < userOTP.length(); i++) {
                String data = String.valueOf(userOTP.charAt(i));

                switch (i) {
                    case 0:
                        edtTxtOTP1.setText(data);
                        break;
                    case 1:
                        edtTxtOTP2.setText(data);
                        break;
                    case 2:
                        edtTxtOTP3.setText(data);
                        break;
                    case 3:
                        edtTxtOTP4.setText(data);
                        break;
                }
            }

            verifyUserOTP(userOTP);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

}

package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class OTPVerificationActivity extends AppCompatActivity {

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
                    verifyUserOTP();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
    }



    private void verifyUserOTP() {

        startActivity(new Intent(this, VerifyImageActivity.class));
    }
}

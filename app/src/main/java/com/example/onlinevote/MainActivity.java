package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText adharNum1, adharNum2, adharNum3;
    private Button login;

    private String adharPart1, adharPart2, adharPart3;

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


//        enterAdharnumber=(EditText)findViewById(R.id.adharnum);
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
                        startActivity(new Intent(MainActivity.this,OTPVerificationActivity.class));

                    }

                    }

        });

    }
}


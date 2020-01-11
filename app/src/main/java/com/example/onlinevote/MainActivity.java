package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText enterAdharnumber;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        enterAdharnumber=(EditText)findViewById(R.id.adharnum);
        login=(Button)findViewById(R.id.logsubmit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String  adharNumber =enterAdharnumber.getText().toString();
                    if(adharNumber.equals("")){
                        Toast.makeText(MainActivity.this,"Field cannot be empty",Toast.LENGTH_SHORT).show();

                    }else if (adharNumber.length()!=12) {

                        Toast.makeText(getApplicationContext(), "pls enter 12 digits", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        startActivity(new Intent(MainActivity.this,Capture.class));

                    }

                    }

        });
    }
    }


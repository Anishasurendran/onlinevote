package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;

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

            }
        });

    }
}

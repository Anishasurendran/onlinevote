package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.onlinevote.adapter.VoterListAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView voterListRecyclerView;
    VoterListAdapter listAdapter;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        voterListRecyclerView = findViewById(R.id.voter_list);

        list = new ArrayList();
        list.add("Anu");
        list.add("Njaan");

        listAdapter = new VoterListAdapter(this, list);

        voterListRecyclerView.setAdapter(listAdapter);
        voterListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        voterListRecyclerView.setHasFixedSize(true);

        listAdapter.notifyDataSetChanged();

    }
}

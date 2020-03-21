package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.widget.Toast;

import com.example.onlinevote.adapter.ElectionListAdapter;
import com.example.onlinevote.config.DataManager;
import com.example.onlinevote.interfaces.RetrofitCallBack;
import com.example.onlinevote.models.Election;

import java.util.ArrayList;

public class
ElectionActivity extends AppCompatActivity {


    ElectionListAdapter electionListAdapter;
    ElectionListAdapter.ElectionListInterface listInterface;
    ArrayList<Election> elections;

    private RecyclerView electionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);

        electionListView = findViewById(R.id.election_list);

        elections = new ArrayList();
        listInterface = new ElectionListAdapter.ElectionListInterface() {
            @Override
            public void onCardClick(int id, String name) {

                startActivity(new Intent(ElectionActivity.this, HomeActivity.class)
                        .putExtra("id", id)
                        .putExtra("name", name));

            }
        };

        getElection();


    }

    public void getElection(){
        DataManager.getDataManager().fetchElections(new RetrofitCallBack<ArrayList<Election>>() {
            @Override
            public void Success(ArrayList<Election> data) {
                electionListAdapter = new ElectionListAdapter(ElectionActivity.this, data, listInterface);
                electionListView.setAdapter(electionListAdapter);
                electionListView.setLayoutManager(new LinearLayoutManager(ElectionActivity.this));
                electionListView.setHasFixedSize(true);
            }

            @Override
            public void Failure(String error) {

            }
        });
    }


}

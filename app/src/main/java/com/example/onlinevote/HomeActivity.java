package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.onlinevote.adapter.VoterListAdapter;
import com.example.onlinevote.config.DataManager;
import com.example.onlinevote.interfaces.RetrofitCallBack;
import com.example.onlinevote.models.Candidate;
import com.example.onlinevote.models.CandidateList;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageButton backButton;
    RecyclerView voterListRecyclerView;
    VoterListAdapter listAdapter;
    ArrayList<Candidate> candidates;
    AlertDialog dialog;

    private TextView electionTitleTextView;

    private  int elecionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int electionId = getIntent().getIntExtra("id", 0);

        voterListRecyclerView = findViewById(R.id.voter_list);
        electionTitleTextView = findViewById(R.id.election_title);
        backButton = findViewById(R.id.voterBackButton);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        electionTitleTextView.setText(getIntent().getStringExtra("name"));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do  you want to confirm your vote?")
        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // FIRE ZE MISSILES!
            }
        })
        .setNegativeButton("deny", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        dialog = builder.create();
        dialog.show();

        fetchCandidates(electionId);




    }

    public void fetchCandidates(int electionId){
        DataManager.getDataManager().fetchCandidates(electionId, new RetrofitCallBack<ArrayList<CandidateList>>() {
            @Override
            public void Success(ArrayList<CandidateList> data) {
                listAdapter = new VoterListAdapter(HomeActivity.this, data);

                voterListRecyclerView.setAdapter(listAdapter);
                voterListRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                voterListRecyclerView.setHasFixedSize(true);

                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void Failure(String error) {

            }
        });
    }

}

package com.example.onlinevote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinevote.adapter.VoterListAdapter;
import com.example.onlinevote.config.DataManager;
import com.example.onlinevote.interfaces.RetrofitCallBack;
import com.example.onlinevote.models.Candidate;
import com.example.onlinevote.models.CandidateList;
import com.example.onlinevote.models.ResponseResult;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageButton backButton;
    private MaterialButton confirmButton, cancelButton;

    LinearLayout votingListLinearLayout, votingCompleteLinearLayout;
    RecyclerView voterListRecyclerView;
    VoterListAdapter listAdapter;
    VoterListAdapter.VoterListInterface listInterface;
    ArrayList<Candidate> candidates;
    CustomDialog dialog;
    CustomDialog.CustomDialogInterface dialogInterface;

    private TextView electionTitleTextView, dialogTextView;

    private  int elecionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int electionId = getIntent().getIntExtra("id", 0);

        voterListRecyclerView = findViewById(R.id.voter_list);
        electionTitleTextView = findViewById(R.id.election_title);
        backButton = findViewById(R.id.voterBackButton);

        votingListLinearLayout =  findViewById(R.id.vote_listing);
        votingCompleteLinearLayout = findViewById(R.id.vote_complete);

        dialogInterface =  new CustomDialog.CustomDialogInterface() {
            @Override
            public void onConfirmButtonClicked(CandidateList candidateList) {
                Toast.makeText(getApplicationContext(), candidateList.getId()+"", Toast.LENGTH_LONG).show();
                vote(candidateList.getId()  );
                // Call data manager for voting
                dialog.dismiss();
            }
        };

        dialog = new CustomDialog(this, dialogInterface);
        dialog.setCancelable(false);

        listInterface = new VoterListAdapter.VoterListInterface() {
            @Override
            public void onVoteButtonClickListener(CandidateList candidate) {
                dialog.setCandidate(candidate);
                dialog.show();
            }
        };

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        electionTitleTextView.setText(getIntent().getStringExtra("name"));
        fetchCandidates(electionId);




    }

    public void fetchCandidates(int electionId){
        DataManager.getDataManager().fetchCandidates(electionId, new RetrofitCallBack<ArrayList<CandidateList>>() {

            @Override
            public void Success(ArrayList<CandidateList> data) {
                listAdapter = new VoterListAdapter(HomeActivity.this, data,listInterface);

                voterListRecyclerView.setAdapter(listAdapter);
                voterListRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                voterListRecyclerView.setHasFixedSize(true);

                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void Failure(String error) {
                if(error.equals("400")){
                    votingCompleteLinearLayout.setVisibility(View.VISIBLE);
                    votingListLinearLayout.setVisibility(View.GONE);
                }
            }
        });
    }


    public  void vote(int electionId){
        DataManager.getDataManager().vote(electionId, new RetrofitCallBack<ResponseResult>() {
            @Override
            public void Success(ResponseResult data) {
                if(data.getSuccess()){
                    Toast.makeText(getApplicationContext(), "Successfully voted", Toast.LENGTH_LONG).show();
                    finish();
                } else{

                    Toast.makeText(getApplicationContext(), "Something went wrong voted", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void Failure(String error) {

            }
        });
    }

}

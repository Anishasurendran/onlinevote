package com.example.onlinevote.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevote.R;
import com.example.onlinevote.models.Candidate;
import com.example.onlinevote.models.CandidateList;

import java.util.ArrayList;

public class VoterListAdapter extends RecyclerView.Adapter<VoterListAdapter.VoteListViewHolder> {

    Context context;
    ArrayList<CandidateList> candidates;
    VoterListInterface voterListInterface;

    public VoterListAdapter(Context context, ArrayList<CandidateList> candidates, VoterListInterface voterListInterface){
        this.context = context;
        this.candidates =  candidates;
        this.voterListInterface =  voterListInterface;
    }

    public  interface VoterListInterface{
        public  void onVoteButtonClickListener(CandidateList candidateList);
    }


    @Override
    public void onBindViewHolder(@NonNull VoteListViewHolder holder, int position) {
        final CandidateList mCandidate =   candidates.get(position);
        holder.candiadteTextView.setText(mCandidate.getCandidate().getName());
        holder.voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voterListInterface.onVoteButtonClickListener(mCandidate);
            }
        });
    }


    @NonNull
    @Override
    public VoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.voterlist, parent, false);
        VoteListViewHolder viewHolder = new VoteListViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }


    public static  class VoteListViewHolder extends RecyclerView.ViewHolder{

        TextView candiadteTextView;
        ImageView symbolImage;
        Button voteButton;
        public VoteListViewHolder(View itemView){
            super(itemView);

            candiadteTextView = itemView.findViewById(R.id.candidatename);
            symbolImage = itemView.findViewById(R.id.symbol);
            voteButton = itemView.findViewById(R.id.candidatebutton);

        }

    }
}

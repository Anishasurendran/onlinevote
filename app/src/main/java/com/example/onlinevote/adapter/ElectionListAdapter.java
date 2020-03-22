package com.example.onlinevote.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevote.R;
import com.example.onlinevote.models.Election;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ElectionListAdapter extends RecyclerView.Adapter<ElectionListAdapter.ElectionListViewHolder> {

    Context mContext;
    ArrayList<Election> elections;
    ElectionListInterface listInterface;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public ElectionListAdapter(Context mContext, ArrayList<Election> elections, ElectionListInterface listInterface){
        this.mContext =  mContext;
        this.elections =  elections;
        this.listInterface = listInterface;
    }


    @Override
    public void onBindViewHolder(@NonNull ElectionListViewHolder holder, int position) {

        final Election election = elections.get(position);
        try {

            Date startDate = format.parse(election.getSdate());
            Date endDate = format.parse(election.getEdate());
            Date curDate = new Date();
            if(startDate.compareTo(curDate) < 0 && endDate.compareTo(curDate) > 0){
                holder.statusTextView.setText("Started");
            } else if(startDate.compareTo(curDate) > 0){
                holder.statusTextView.setText("Not Started");
            } else if(endDate.compareTo(curDate) < 0)
                holder.statusTextView.setText("Finished");
        }catch (Exception e){
            Log.d("EInfo", e.toString());
        }
        holder.electionNameTextView.setText(election.getElectionName());
        holder.candidateCountTextView.setText(election.getCount() + " candidates");
        holder.electionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listInterface.onCardClick(election.getId(), election.getElectionName());
            }
        });

    }

    @NonNull
    @Override
    public ElectionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.election_list_view, parent, false);
        ElectionListViewHolder viewHolder = new ElectionListViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return elections.size();
    }

    public class ElectionListViewHolder  extends RecyclerView.ViewHolder{

        MaterialCardView electionCard;
        TextView electionNameTextView, statusTextView, candidateCountTextView;

        public  ElectionListViewHolder(View itemView){
            super(itemView);
            electionCard =  itemView.findViewById(R.id.election_card);
            electionNameTextView = itemView.findViewById(R.id.electionName);
            statusTextView = itemView.findViewById(R.id.electionStatus);
            candidateCountTextView = itemView.findViewById(R.id.candidateCount);
        }
    }

    public interface ElectionListInterface {
        public void onCardClick(int id, String electionName);
    }
}

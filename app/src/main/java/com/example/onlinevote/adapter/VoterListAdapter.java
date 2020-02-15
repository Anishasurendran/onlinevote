package com.example.onlinevote.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevote.R;

import java.util.ArrayList;

public class VoterListAdapter extends RecyclerView.Adapter<VoterListAdapter.VoteListViewHolder> {

    Context context;
    ArrayList<String> list;

    public VoterListAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.list =  list;
    }


    @Override
    public void onBindViewHolder(@NonNull VoteListViewHolder holder, int position) {
        holder.candiadteTextView.setText(list.get(position));
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
        return list.size();
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

/*
GameLogAdapter.java
Created by Andrew Leamy
Oct 28 2019
Acts as an adapter for the game log table, so that it can be updated dynamically
by the RecyclerView (I don't really understand this process myself)
*/

package com.ucsc.winrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ucsc.winrate.table_entities.GameLogEntry;
import com.ucsc.winrate.table_entities.OpponentProfile;

import java.util.ArrayList;
import java.util.List;

public class OpponentProfileAdapter extends RecyclerView.Adapter<OpponentProfileAdapter.OpponentProfileViewHolder> {

    class OpponentProfileViewHolder extends RecyclerView.ViewHolder {
        private final TextView opponentProfileId;
        private final TextView opponentName;

        private OpponentProfileViewHolder(View itemView) {
            super(itemView);
            opponentProfileId = itemView.findViewById(R.id.opponent_profile_id);
            opponentName = itemView.findViewById(R.id.opponent_profile_opponent_name);
        }
    }

    private final LayoutInflater mInflater;
    private List<OpponentProfile> opponentProfiles; // Cached copy of words

    public OpponentProfileAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public OpponentProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.opponent_profile_table_item, parent, false);
        return new OpponentProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OpponentProfileViewHolder holder, int position) {
        if (opponentProfiles != null) {
            OpponentProfile current = opponentProfiles.get(position);
            holder.opponentName.setText(current.getNickname());
            //holder.opponentProfileId.setText(Integer.toString(current.getId()));
            holder.opponentProfileId.setText(Integer.toString(position) + 1);
        } else {
            // Covers the case of data not being ready yet.
            holder.opponentProfileId.setText("N/A");
            holder.opponentName.setText("N/A");
        }
    }

    public List<OpponentProfile> getAllOpponentProfiles(){
        return this.opponentProfiles;
    }

    public void setOpponentProfiles(List<OpponentProfile> opponentProfiles){
        this.opponentProfiles = opponentProfiles;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // gameLogEntries has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (opponentProfiles != null)
            return opponentProfiles.size();
        else return 0;
    }
}
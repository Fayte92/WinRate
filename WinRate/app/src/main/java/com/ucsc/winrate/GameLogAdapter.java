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

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsc.winrate.table_entities.GameLogEntry;

import java.util.ArrayList;
import java.util.List;

public class GameLogAdapter extends RecyclerView.Adapter<GameLogAdapter.GameLogViewHolder> {

    class GameLogViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewWinStatus;
        private TextView textViewOpponentName;
        private TextView textViewDate;
        private TextView textViewOpponentDeck;

        private GameLogViewHolder(View itemView) {
            super(itemView);
            this.textViewWinStatus = itemView.findViewById(R.id.text_view_win_status);
            this.textViewOpponentName = itemView.findViewById(R.id.text_view_opponent_name);
            this.textViewDate = itemView.findViewById(R.id.text_view_date);
            this.textViewOpponentDeck = itemView.findViewById(R.id.text_view_opponent_deck);

        }
    }

    private final LayoutInflater mInflater;
    private List<GameLogEntry> gameLogEntries; // Cached copy of game log table

    public GameLogAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public GameLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.game_log_table_item, parent, false);
        return new GameLogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameLogViewHolder holder, int position) {
        if (gameLogEntries != null) {
            GameLogEntry current = gameLogEntries.get(position);
            if(current.getWinStatus()){
                holder.textViewWinStatus.setText("Win");
            } else {
                holder.textViewWinStatus.setText("Loss");
            }
            holder.textViewOpponentName.setText(current.getOpponentName());
            holder.textViewDate.setText(current.getDate());
            holder.textViewOpponentDeck.setText(current.getOpponentDeck());
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewWinStatus.setText("N/A");
            holder.textViewOpponentName.setText("N/A");
            holder.textViewDate.setText("N/A");
            holder.textViewOpponentDeck.setText("N/A");
        }
    }

    public List<GameLogEntry> getAllGameLogEntries(){
        return this.gameLogEntries;
    }

    public void setGameLogEntries(List<GameLogEntry> gameLogEntries){
        this.gameLogEntries = gameLogEntries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (gameLogEntries != null)
            return gameLogEntries.size();
        else return 0;
    }
}
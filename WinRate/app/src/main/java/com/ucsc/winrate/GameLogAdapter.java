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

import java.util.List;

public class GameLogAdapter extends RecyclerView.Adapter<GameLogAdapter.GameLogViewHolder> {

    class GameLogViewHolder extends RecyclerView.ViewHolder {
        private final TextView gameLogView;

        private GameLogViewHolder(View itemView) {
            super(itemView);
            gameLogView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<GameLogEntry> gameLogEntries; // Cached copy of words

    GameLogAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public GameLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new GameLogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameLogViewHolder holder, int position) {
        if (gameLogEntries != null) {
            GameLogEntry current = gameLogEntries.get(position);
            holder.gameLogView.setText(current.getOpponentName()); //TODO: add rest of columns
        } else {
            // Covers the case of data not being ready yet.
            holder.gameLogView.setText("N/A");
        }
    }

    void setGameLogEntries(List<GameLogEntry> gameLogEntries){
        this.gameLogEntries = gameLogEntries;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // gameLogEntries has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (gameLogEntries != null)
            return gameLogEntries.size();
        else return 0;
    }
}
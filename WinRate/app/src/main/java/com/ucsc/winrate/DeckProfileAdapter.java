package com.ucsc.winrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ucsc.winrate.table_entities.DeckProfile;

import java.util.ArrayList;
import java.util.List;

public class DeckProfileAdapter extends RecyclerView.Adapter<DeckProfileAdapter.DeckProfileViewHolder> {

    class DeckProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewOwner;

        private DeckProfileViewHolder(View itemView) {
            super(itemView);
            this.textViewId = itemView.findViewById(R.id.deck_profile_id);
            this.textViewName = itemView.findViewById(R.id.deck_profile_name);
            this.textViewOwner = itemView.findViewById(R.id.deck_profile_owner);
        }
    }

    private final LayoutInflater deckInflater;
    private List<DeckProfile> deckProfiles = new ArrayList<>(); //cached copy of deck profiles table

    public DeckProfileAdapter(Context context){
        deckInflater = LayoutInflater.from(context);
    }

    @Override
    public DeckProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = deckInflater.inflate(R.layout.deck_profile_table_item, parent, false);
        return new DeckProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeckProfileViewHolder holder, int position) {
        if (deckProfiles != null) {
            DeckProfile current = deckProfiles.get(position);
            holder.textViewId.setText(current.getId());
            holder.textViewName.setText(current.getName());
            holder.textViewOwner.setText(current.getOwner());
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewId.setText("N/A");
            holder.textViewName.setText("N/A");
            holder.textViewOwner.setText("N/A");
        }
    }

    public List<DeckProfile> getAllDeckProfiles(){
        return this.deckProfiles;
    }

    public void setDeckProfiles(List<DeckProfile> deckProfiles) {
        this.deckProfiles = deckProfiles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (deckProfiles != null)
            return deckProfiles.size();
        else return 0;
    }
}

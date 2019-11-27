package com.ucsc.winrate.ui.contactBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsc.winrate.DeckProfileAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.table_entities.DeckProfile;

import java.util.List;

public class DecksFragment extends Fragment implements View.OnClickListener{

    private DecksViewModel decksViewModel;
    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        decksViewModel = new ViewModelProvider(this).get(DecksViewModel.class);

        View root = inflater.inflate(R.layout.fragment_decks, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_deck_profile_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final DeckProfileAdapter adapter = new DeckProfileAdapter(getActivity());

        recyclerView.setAdapter(adapter);

        fab = root.findViewById(R.id.decksInputButton);
        fab.setOnClickListener(this);

        decksViewModel.getAllDeckProfiles().observe(this, new Observer<List<DeckProfile>>() {
            @Override
            public void onChanged(List<DeckProfile> deckProfiles) {
                adapter.setDeckProfiles(deckProfiles);
            }
        });

        return root;
    }

    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_contact_fragment);
        switch (view.getId()) {
            case R.id.decksInputButton:
                navController.navigate(R.id.navContactsInput);
                break;
        }
    }
}
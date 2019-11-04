package com.ucsc.winrate.ui.contactBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ucsc.winrate.R;

public class DecksFragment extends Fragment{

    private DecksViewModel decksViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        decksViewModel =
                ViewModelProviders.of(this).get(DecksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_decks, container, false);
        //final TextView textView = root.findViewById(R.id.text_decks);
        //decksViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }
}